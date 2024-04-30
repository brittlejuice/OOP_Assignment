package com.example.demo.model.requests;

import lombok.*;

import jakarta.persistence.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;

public class PostDelete {

    private int postID;

    public int getPostID() {
        return this.postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }


}
