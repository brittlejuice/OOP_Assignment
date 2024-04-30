package com.example.demo.model;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PostTable")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;

    @Column
    private String postBody;
    
    // Consider using @CreationTimestamp for automatic date handling
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    // Define the many-to-one relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Define the one-to-many relationship with Comment
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
