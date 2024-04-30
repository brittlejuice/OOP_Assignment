package com.example.demo.model.requests;

public class CommentEdit {
    private String commentBody;

    public String getCommentBody() {
        return this.commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getCommentID() {
        return this.commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
    private int commentID;
}
