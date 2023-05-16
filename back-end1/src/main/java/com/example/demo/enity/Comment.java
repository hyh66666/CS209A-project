package com.example.demo.enity;

public class Comment {
    private Integer id;
    private String edited;
    private String score;
    private String creationDate;
    private String postId;
    private String commentId;
    private String contentLicense;
    private String userId;

    public Comment(String edited, String score, String creationDate, String postId, String commentId, String contentLicense,
                   String userId) {
        this.edited = edited;
        this.score = score;
        this.creationDate = creationDate;
        this.postId = postId;
        this.commentId = commentId;
        this.contentLicense = contentLicense;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getEdited() {
        return edited;
    }

    public String getScore() {
        return score;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getPostId() {
        return postId;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getContentLicense() {
        return contentLicense;
    }
}
