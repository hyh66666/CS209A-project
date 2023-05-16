package com.example.demo.enity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class question {

    private Integer id;
    private String tags;
    private String isAnswered;
    private String viewCount;
    private String acceptedAnswerId;
    private String answerCount;
    private String score;
    private String lastActivityDate;
    private String creationDate;
    private String questionId;
    private String contentLicense;
    private String link;
    private String title;
    private String userId;

    public question(String tags, String isAnswered, String viewCount, String acceptedAnswerId, String answerCount,
                    String score, String lastActivityDate, String creationDate, String questionId, String contentLicense,
                    String link, String title, String userId) {
        this.tags = tags;
        this.isAnswered = isAnswered;
        this.viewCount = viewCount;
        this.acceptedAnswerId = acceptedAnswerId;
        this.answerCount = answerCount;
        this.score = score;
        this.lastActivityDate = lastActivityDate;
        this.creationDate = creationDate;
        this.questionId = questionId;
        this.contentLicense = contentLicense;
        this.link = link;
        this.title = title;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTags() {
        return tags;
    }

    public String getIsAnswered() {
        return isAnswered;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public String getAnswerCount() {
        return answerCount;
    }

    public String getScore() {
        return score;
    }

    public String getLastActivityDate() {
        return lastActivityDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getContentLicense() {
        return contentLicense;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }
}
