package com.example.demo.model;


import java.util.ArrayList;
import java.util.List;

public class UserClass {

    private String name;
    private int UserID;
    private String email;
    private String password;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return this.UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostClass> getPosts() {
        return this.posts;
    }

    public void setPosts(List<PostClass> posts) {
        this.posts = posts;
    }
    private List<PostClass> posts = new ArrayList<>();

    //private List<Long> posts = new ArrayList<>();
}
