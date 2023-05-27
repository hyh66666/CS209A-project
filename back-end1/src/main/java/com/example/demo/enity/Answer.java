package com.example.demo.enity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Answer {
  private Integer id;
  private String isAccepted;
  private String score;
  private String lastActivityDate;
  private String creationDate;
  private String answerId;
  private String questionId;
  private String contentLicense;
  private String userId;


    public Answer(String isAccepted, String score, String lastActivityDate, String creationDate, String answerId,
                  String questionId, String contentLicense, String userId) {
      this.isAccepted = isAccepted;
      this.score = score;
      this.lastActivityDate = lastActivityDate;
      this.creationDate = creationDate;
      this.answerId = answerId;
      this.questionId = questionId;
      this.contentLicense = contentLicense;
      this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public String getUserId() {
        return userId;
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

    public String getAnswerId() {
        return answerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getContentLicense() {
        return contentLicense;
    }
}
