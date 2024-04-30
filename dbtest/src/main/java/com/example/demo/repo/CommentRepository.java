package com.example.demo.repo;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByPost(Post post);
}