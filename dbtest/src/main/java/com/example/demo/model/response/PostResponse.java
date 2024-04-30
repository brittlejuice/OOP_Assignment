package com.example.demo.model.response;

import java.sql.Date;
import java.util.List;

public class PostResponse {
    private int postID;
    private String postBody;
    private Date date;
    private List<CommentResponse> comments;

    public int getPostID() {
        return this.postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return this.postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(java.sql.Date date2) {
        this.date = date2;
    }

    public List<CommentResponse> getComments() {
        return this.comments;
    }

    public void setComments(List<CommentResponse> commentResponses) {
        this.comments = commentResponses;
    }

}
