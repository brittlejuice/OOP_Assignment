package com.example.demo.repo;

import com.example.demo.model.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Modifying
    @Query("UPDATE Post p SET p.postBody = :postBody WHERE p.postId = :postId")
    void updatePostBody(@Param("postId") int postId, @Param("postBody") String postBody);

    List<Post> findAllByOrderByDateDesc();
}