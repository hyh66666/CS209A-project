package com.example.demo.controller;

import com.example.demo.enity.Answer;
import com.example.demo.enity.Comment;
import com.example.demo.enity.Owner;
import com.example.demo.enity.question;
import com.example.demo.mapper.AnswerMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.OwnerMapper;
import com.example.demo.mapper.QuestionMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

@RestController
public class OwnerController {

    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private CommentMapper commentMapper;

    @PostMapping("/addData")
    public String addData() throws IOException, InterruptedException {
        for (int i = 1; i < 7; i++) {
            String apiUrl = "https://api.stackexchange.com/2.3/questions?order=desc&tagged=java&" +
                    "site=stackoverflow&pagesize=100&page=" + i;
            String jsonString = getData(apiUrl);
            System.out.println(jsonString);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode itemsNode = jsonNode.get("items");
            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    JsonNode ownerNode = itemNode.get("owner");
                    String user_id = addOwner(ownerNode, ownerMapper);
                    // user_id not exist
                    if (user_id == null) {
                        continue;
                    }
                    String question_id = addQuestion(itemNode, questionMapper, user_id);
                    // question already exist
                    if (question_id == null){
                        continue;
                    }
                    String apiURL = "https://api.stackexchange.com/2.3/questions/" + question_id +
                            "/answers?order=desc&sort=activity&site=stackoverflow";
                    String answer_json = OwnerController.getData(apiURL);
                    ObjectMapper answer_objectMapper = new ObjectMapper();
                    JsonNode answer_jsonNode = answer_objectMapper.readTree(answer_json);
                    JsonNode answer_itemsNode = answer_jsonNode.get("items");
                    if(answer_itemsNode == null) {
                        continue;
                    }
                    if (answer_itemsNode.isArray()) {
                        for (JsonNode answer_itemNode : answer_itemsNode) {
                            JsonNode answer_ownerNode = answer_itemNode.get("owner");
                            String answer_user_id = addOwner(answer_ownerNode, ownerMapper);
                            if (answer_user_id == null) {
                                continue;
                            }
                            addAnswer(answer_itemNode, answerMapper, answer_user_id);
                        }
                    }
                    String api_url = "https://api.stackexchange.com/2.3/questions/" + question_id +
                            "/comments?order=desc&sort=creation&site=stackoverflow";
                    String comment_json = OwnerController.getData(api_url);
                    ObjectMapper comment_objectMapper = new ObjectMapper();
                    JsonNode comment_jsonNode = comment_objectMapper.readTree(comment_json);
                    JsonNode comment_itemsNode = comment_jsonNode.get("items");
                    if(comment_itemsNode == null) {
                        continue;
                    }
                    if (comment_itemsNode.isArray()) {
                        for (JsonNode comment_itemNode : comment_itemsNode) {
                            JsonNode comment_ownerNode = comment_itemNode.get("owner");
                            String comment_user_id = addOwner(comment_ownerNode, ownerMapper);
                            if (comment_user_id == null) {
                                continue;
                            }
                            addComment(comment_itemNode, commentMapper, comment_user_id);
                        }
                    }
                }
            }
        }

        return "finish!";
    }


    public static String getData(String apiUrl) throws IOException, InterruptedException {
//        Thread.sleep(500);
        Thread.sleep(100);
        String key = "&key=BVCuPICqf0pnW99TTmrgHQ((";
        apiUrl = apiUrl + key;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept-Encoding", "gzip");
        connection.setRequestProperty("Content-Type", "application/json");
        InputStream inputStream = connection.getResponseCode() < 400 ? connection.getInputStream() : connection.getErrorStream();
        if ("gzip".equals(connection.getContentEncoding())) { // 检查响应是否被压缩
            inputStream = new GZIPInputStream(inputStream);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        String result = responseBuilder.toString();
        reader.close();
        return result;
    }

    public static String addOwner(JsonNode ownerNode, OwnerMapper ownerMapper){
        JsonNode accountIdNode = ownerNode.get("account_id");
        JsonNode reputationNode = ownerNode.get("reputation");
        JsonNode user_idNode = ownerNode.get("user_id");
        JsonNode user_typeNode = ownerNode.get("user_type");
        JsonNode profile_imageNode = ownerNode.get("profile_image");
        JsonNode display_nameNode = ownerNode.get("display_name");
        JsonNode linkNode = ownerNode.get("link");

        String account_id = "";
        if (accountIdNode != null) {
            account_id = accountIdNode.toString();
        }
        String reputation = "";
        if (reputationNode != null) {
            reputation = reputationNode.toString();
        }
        if (user_idNode == null) {
            return null;
        }
        String user_id = user_idNode.toString();
        String user_type = user_typeNode.toString();
        String profile_image = "";
        if (profile_imageNode != null) {
            profile_image = profile_imageNode.toString();
        }
        String display_name = "";
        if (display_nameNode != null) {
            display_name = display_nameNode.toString();
        }
        String link = "";
        if (linkNode != null) {
            link = linkNode.toString();
        }
        Owner owner = new Owner(account_id, reputation, user_id, user_type, profile_image, display_name,
                link);
        Owner temp =  ownerMapper.findOwnerByUserId(user_id);
        if (temp == null) {
            ownerMapper.insert(owner);
        }
        return user_id;
    }

    public static String addQuestion(JsonNode itemNode, QuestionMapper questionMapper, String user_id) {
        JsonNode tagsNode = itemNode.get("tags");
        JsonNode isAnsweredNode = itemNode.get("is_answered");
        JsonNode view_countNode = itemNode.get("view_count");
        JsonNode accepted_answer_iddNode = itemNode.get("accepted_answer_id");
        JsonNode answer_countNode = itemNode.get("answer_count");
        JsonNode scoreNode = itemNode.get("score");
        JsonNode last_activity_dateNode = itemNode.get("last_activity_date");
        JsonNode creation_dateNode = itemNode.get("creation_date");
        JsonNode question_idNode = itemNode.get("question_id");
        JsonNode content_licenseNode = itemNode.get("content_license");
        JsonNode item_linkNode = itemNode.get("link");
        JsonNode titleNode = itemNode.get("title");

        String tags = tagsNode.toString();
        String is_answered = isAnsweredNode.toString();
        String view_count = view_countNode.toString();
        String accepted_answer_id = "";
        if (accepted_answer_iddNode != null) {
            accepted_answer_id = accepted_answer_iddNode.toString();
        }
        String answer_count = answer_countNode.toString();
        String score = scoreNode.toString();
        String last_activity_date = last_activity_dateNode.toString();
        String creation_date = creation_dateNode.toString();
        String question_id = question_idNode.toString();
        String content_license = "";
        if (content_licenseNode != null) {
            content_license = content_licenseNode.toString();
        }
        String item_link = item_linkNode.toString();
        String title = titleNode.toString();

        question question = new question(tags, is_answered, view_count, accepted_answer_id,
                answer_count, score, last_activity_date, creation_date, question_id, content_license,
                item_link, title, user_id);
        question question_temp =  questionMapper.findQuestionByQuestionId(question_id);
        if (question_temp == null) {
            questionMapper.insert(question);
            return question_id;
        }
        return null;
    }

    public static void addAnswer(JsonNode answer_itemNode, AnswerMapper answerMapper, String answer_user_id){
        JsonNode answer_is_acceptedNode = answer_itemNode.get("is_accepted");
        JsonNode answer_scoreNode = answer_itemNode.get("score");
        JsonNode answer_last_activity_dateNode = answer_itemNode.get("last_activity_date");
        JsonNode answer_creation_dateNode = answer_itemNode.get("creation_date");
        JsonNode answer_answer_idNode = answer_itemNode.get("answer_id");
        JsonNode answer_question_idNode = answer_itemNode.get("question_id");
        JsonNode answer_content_licensedNode = answer_itemNode.get("content_license");

        String answer_is_accepted = answer_is_acceptedNode.toString();
        String answer_score = answer_scoreNode.toString();
        String answer_last_activity_date = answer_last_activity_dateNode.toString();
        String answer_creation_date = answer_creation_dateNode.toString();
        String answer_answer_id = answer_answer_idNode.toString();
        String answer_question_id = answer_question_idNode.toString();
        String answer_content_licensed = answer_content_licensedNode.toString();

        Answer answer = new Answer(answer_is_accepted, answer_score, answer_last_activity_date,
                answer_creation_date, answer_answer_id, answer_question_id, answer_content_licensed, answer_user_id);
        answerMapper.insert(answer);
    }

    public static void addComment(JsonNode comment_itemNode, CommentMapper commentMapper, String comment_user_id){
        JsonNode comment_editedNode = comment_itemNode.get("edited");
        JsonNode comment_scoreNode = comment_itemNode.get("score");
        JsonNode comment_creation_dateNode = comment_itemNode.get("creation_date");
        JsonNode comment_post_idNode = comment_itemNode.get("post_id");
        JsonNode comment_idNode = comment_itemNode.get("comment_id");
        JsonNode comment_content_licensedNode = comment_itemNode.get("content_license");

        String comment_edited = comment_editedNode.toString();
        String comment_score = comment_scoreNode.toString();
        String comment_creation_date = comment_creation_dateNode.toString();
        String comment_post_id = comment_post_idNode.toString();
        String comment_id = comment_idNode.toString();
        String comment_content_licensed = comment_content_licensedNode.toString();

        Comment comment = new Comment(comment_edited, comment_score, comment_creation_date, comment_post_id, comment_id,
                comment_content_licensed, comment_user_id);
        commentMapper.insert(comment);
    }
}

