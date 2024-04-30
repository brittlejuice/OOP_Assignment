package com.example.demo.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class PostClass {

    private int postID;

    private String postBody;

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

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CommentClass> getComments() {
        return this.comments;
    }

    public void setComments(List<CommentClass> comments) {
        this.comments = comments;
    }

    private Date date = new Date();

    private List<CommentClass> comments = new ArrayList<>();


}
