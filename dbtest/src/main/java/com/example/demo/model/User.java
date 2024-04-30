package com.example.demo.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UserTable")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {

    @Column
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // @OneToOne(mappedBy = "posttest")
    //Post posttest;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "commentCreator", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    //private List<Long> posts = new ArrayList<>();
}
