package com.example.demo.model.requests;

import lombok.*;

import jakarta.persistence.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;

public class PostCreation {

    private String postBody;
    private int userID;
    
    public PostCreation(String postBody, int userID) {
        this.postBody = postBody;
        this.userID = userID;
    }

    public String getPostBody() {
        return this.postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    // @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    // private List<Comment> comments = new ArrayList<>();

}
