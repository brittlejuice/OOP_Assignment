package com.example.demo.repo;

import com.example.demo.model.User;

import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    java.util.Optional<User> findByEmail(String email);
    
}