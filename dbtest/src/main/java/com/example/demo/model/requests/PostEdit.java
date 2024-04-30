package com.example.demo.model.requests;

import lombok.*;

import jakarta.persistence.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;

public class PostEdit {

    private String postBody;
    private int postID;

    public String getPostBody() {
        return this.postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getPostID() {
        return this.postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }


    // @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    // private List<Comment> comments = new ArrayList<>();

}
