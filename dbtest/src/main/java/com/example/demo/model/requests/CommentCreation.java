package com.example.demo.model.requests;

public class CommentCreation {
    private String commentBody;
    private int postID;
    private int userID;

    public String getCommentBody() {
        return this.commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getPostID() {
        return this.postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
