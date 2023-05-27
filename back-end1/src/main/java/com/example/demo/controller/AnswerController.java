package com.example.demo.controller;

import com.example.demo.enity.Answer;
import com.example.demo.enity.question;
import com.example.demo.mapper.AnswerMapper;
import com.example.demo.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AnswerController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private AnswerMapper answerMapper;

    @RequestMapping("/answerAccepted1")
    public Map<String, String> answer_accepted1() {
        Map<String, String> result = new HashMap<>();
        List<question> questionList = questionMapper.findAllQuestion();
        int total = questionList.size();
        int count = (answerMapper.findAnswerAccepted()).size();
        float per = (float) (count / (total * 1.0));
//        result.put("展示有 accepted answer 的问题", String.valueOf(count));
//        result.put("total", String.valueOf(total));
        result.put("有 accepted answer 的问题", String.valueOf(per));
        result.put("没有 accepted answer 的问题", String.valueOf(1-per));
        return result;
    }

    @RequestMapping("/answerAccepted2")
    public Map<String, Integer> answer_accepted2() {
        Map<String, Double> map = new HashMap<>();
        List<Answer> answerList = answerMapper.findAnswerAccepted();
        double max_time = 0;
        for (Answer answer : answerList) {
            question question = questionMapper.findQuestionByQuestionId(answer.getQuestionId());
            long epochTime = Long.parseLong(answer.getLastActivityDate());
            long epochTime1 = Long.parseLong(question.getCreationDate());
            double time = (epochTime - epochTime1) / 60.0;
            map.put(question.getQuestionId(), time);
            if (time > max_time) {
                max_time = time;
            }
        }
        max_time = Math.ceil(max_time);
        Map<String, List<String>> result = new HashMap<>();
        Map<String, Integer> final_result = new HashMap<>();
        double duration_value =  (max_time / 10);
        for (int i = 0; i < 10; i++) {
            String duration = i * duration_value + "min-" + (i+1) * duration_value + "min";
            List<String> list = new ArrayList<>();
            result.put(""+i, list);
            final_result.put(""+i, 0);
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                if (entry.getValue() > i * duration_value && entry.getValue() < (i+1) * duration_value) {
                    result.get(""+i).add(entry.getKey());
                    int value = final_result.get(""+i);
                    value += 1;
                    final_result.put(""+i, value);
                }
            }
        }
        return final_result;
    }

    @RequestMapping("/answerAccepted2ok")
    public Double answer_accepted2ok() {
        Map<String, Double> map = new HashMap<>();
        List<Answer> answerList = answerMapper.findAnswerAccepted();
        double max_time = 0;
        for (Answer answer : answerList) {
            question question = questionMapper.findQuestionByQuestionId(answer.getQuestionId());
            long epochTime = Long.parseLong(answer.getLastActivityDate());
            long epochTime1 = Long.parseLong(question.getCreationDate());
            double time = (epochTime - epochTime1) / 60.0;
            map.put(question.getQuestionId(), time);
            if (time > max_time) {
                max_time = time;
            }
        }
        max_time = Math.ceil(max_time);
        Map<String, List<String>> result = new HashMap<>();
        Map<String, Integer> final_result = new HashMap<>();
        double duration_value =  (max_time / 10);
        return duration_value;
    }

    @RequestMapping("/answerAccepted3")
    public Map<String, String> answer_accepted3() {
        Map<String, String> result = new HashMap<>();
        List<question> questionList = questionMapper.findAllQuestion();
        int count = 0;
        int total = questionList.size();
        for (question question : questionList) {
            List<Answer> answerList = answerMapper.findAnswerByQuestion_id(question.getQuestionId());
            if (!answerList.isEmpty()){
                List<Integer> non_accept = new ArrayList<>();
                int accept_score = Integer.MIN_VALUE;
                for (Answer answer : answerList) {
                    if (answer.getIsAccepted().equals("true")) {
                        accept_score = Integer.parseInt(answer.getScore());
                    } else {
                        non_accept.add(Integer.parseInt(answer.getScore()));
                    }
                }
                if (accept_score != Integer.MIN_VALUE) {
                    for (Integer integer : non_accept) {
                        if (integer > accept_score) {
                            count = count + 1;
                            break;
                        }
                    }
                }
            }
        }
        float per = (float) (count / (total * 1.0));
//        result.put("count", String.valueOf(count));
//        result.put("total", String.valueOf(total));
        result.put("含有 non-accepted answer 的 upvote 数高于 accepted answer 的问题", String.valueOf(per));
        result.put("含有 non-accepted answer 的 upvote 数并不高于 accepted answer 的问题", String.valueOf(1-per));
        return result;
    }
}
