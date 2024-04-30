package com.example.demo.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class CommentClass {
    private int commentID;
    private String commentBody;
    private CommentCreator commentCreator;

    public int getCommentID() {
        return this.commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return this.commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public CommentCreator getCommentCreator() {
        return this.commentCreator;
    }

    public void setCommentCreator(CommentCreator commentCreator) {
        this.commentCreator = commentCreator;
    }
}

