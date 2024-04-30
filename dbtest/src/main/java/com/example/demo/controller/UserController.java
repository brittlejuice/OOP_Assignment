package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.example.demo.model.*;
import com.example.demo.model.requests.*;
import com.example.demo.model.response.*;
import com.example.demo.repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
public class UserController {

    List<UserClass> users = new ArrayList<>();

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        // Check if the user exists
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        User user = null;
        for(User a:userList)
        {
            if(a.getEmail().equals(userLoginRequest.getEmail()))
            {
                user = a;
            }
        }
        if (user == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        }

        // Check if the password matches
        if (!user.getPassword().equals(userLoginRequest.getPassword())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Username/Password Incorrect");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password Incorrect");
        }

        // Login successful
        return ResponseEntity.ok("Login Successful");
    }


       @PostMapping("/signup")
    public ResponseEntity<?> SignUp(@RequestBody User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Forbidden, Account already exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Forbidden, Account already exists");
        } else {
            User userObj = userRepository.save(user);
            return ResponseEntity.ok("Account Creation Successful");
        }
    }


@PostMapping("/post")
public ResponseEntity<?> postCreate(@RequestBody PostCreation request) 
{
    Optional<User> userOptional = userRepository.findById(request.getUserID());
    if (userOptional.isPresent()) {
        User user = userOptional.get();
        Post post = new Post();
        post.setPostBody(request.getPostBody());
        post.setUser(user);
        postRepository.save(post);
        return ResponseEntity.ok("Post created successfully");
    } else {
        Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
    }
}
    
    @PostMapping("/comment")
    public ResponseEntity<?> commentCreate(@RequestBody CommentCreation cc) {
        Optional<User> userOptional = userRepository.findById(cc.getUserID());
        Optional<Post> postOptional = postRepository.findById(cc.getPostID());
        if (!userOptional.isPresent())
        {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        if (!postOptional.isPresent())
        {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Post does not exist");
        

        else {
            User user = userOptional.get();
            Post post = postOptional.get();
            Comment comment = new Comment();
            comment.setCommentBody(cc.getCommentBody());
            comment.setCommentCreator(user);
            comment.setPost(post);
            commentRepository.save(comment);
            return ResponseEntity.ok("Comment created successfully");
        } 
    }

    @PatchMapping("/post")
    public ResponseEntity<?> postEdit(@RequestBody PostEdit pe) {
        Optional<Post> postOptional = postRepository.findById(pe.getPostID());
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setPostBody(pe.getPostBody());
            postRepository.save(post);
            return ResponseEntity.ok("Post edited successfully");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Post does not exist");
        }
    }

    @PatchMapping("/comment")
    public ResponseEntity<?> commentEdit(@RequestBody CommentEdit ce) {
        Optional<Comment> commentOptional = commentRepository.findById(ce.getCommentID());
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setCommentBody(ce.getCommentBody());
            commentRepository.save(comment);
            return ResponseEntity.ok("Comment edited successfully");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Comment does not exist");
        }
    }


    @DeleteMapping("/post")
    public ResponseEntity<?> postDelete(@RequestParam("postID") int postID) {
    Optional<Post> postOptional = postRepository.findById(postID);
    if (postOptional.isPresent()) {
        Post post = postOptional.get();
        postRepository.delete(post);
        return ResponseEntity.ok("Post deleted");
    } else {
        Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
    }
    }
    

    @DeleteMapping("/comment")
    public ResponseEntity<?> commentDelete(@RequestParam("commentID") int commentID) {
        Optional<Comment> commentOptional = commentRepository.findById(commentID);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            commentRepository.delete(comment);
            return ResponseEntity.ok("Comment deleted");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
    }
    

    @GetMapping("/user")
    public ResponseEntity<?> userInfo(@RequestParam("userID") int userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDetails responseBody = new UserDetails();
            responseBody.setName(user.getName());
            responseBody.setEmail(user.getEmail());
            responseBody.setUserID(user.getUserID());
            return ResponseEntity.ok(responseBody);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }
        @GetMapping("/post")
        public ResponseEntity<?> postInfo(@RequestParam("postID") int postID) {
            Optional<Post> postOptional = postRepository.findById(postID);
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                List<Comment> comments = commentRepository.findByPost(post);
                List<CommentResponse> commentResponses = new ArrayList<>();
                for (Comment comment : comments) {
                    CommentResponse commentResponse = new CommentResponse();
                    commentResponse.setCommentID(comment.getCommentId());
                    commentResponse.setCommentBody(comment.getCommentBody());
                    CommentCreator commentCreator = new CommentCreator();
                    commentCreator.setUserID(comment.getCommentCreator().getUserID());
                    commentCreator.setName(comment.getCommentCreator().getName());
                    commentResponse.setCommentCreator(commentCreator);
                    commentResponses.add(commentResponse);
                }
                PostResponse postResponse = new PostResponse();
                postResponse.setPostID(post.getPostId());
                postResponse.setPostBody(post.getPostBody());
                //postResponse.setDate( post.getDate());
                postResponse.setDate(new java.sql.Date(post.getDate().getTime()));
                postResponse.setComments(commentResponses);
                return ResponseEntity.ok(postResponse);
                //return ResponseEntity.ok(post);
            } else {
                Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
                //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
            }
        }


    @GetMapping("/comment")
    public ResponseEntity<?> commentRetrieve(@RequestParam("commentID") int commentID) {
        Optional<Comment> commentOptional = commentRepository.findById(commentID);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            CommentResponse responseBody = new CommentResponse();
            responseBody.setCommentID(comment.getCommentId());
            responseBody.setCommentBody(comment.getCommentBody());
            CommentCreator commentCreator = new CommentCreator();
            commentCreator.setUserID(comment.getCommentCreator().getUserID());
            commentCreator.setName(comment.getCommentCreator().getName());
            responseBody.setCommentCreator(commentCreator);
            return ResponseEntity.ok(responseBody);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
    }


    @GetMapping("/users")
    public ResponseEntity<?> allUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDetails> userDetailsList = new ArrayList<>();
        for(User user : userList)
        {
            UserDetails ud = new UserDetails();
            ud.setName(user.getName());
            ud.setEmail(user.getEmail());
            ud.setUserID(user.getUserID());
            userDetailsList.add(ud);
        }
        return ResponseEntity.ok(userDetailsList);
    }









    @GetMapping("/")
    public ResponseEntity<?> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByDateDesc();
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : posts) {
            List<Comment> comments = commentRepository.findByPost(post);
            List<CommentResponse> commentResponses = new ArrayList<>();
            for (Comment comment : comments) {
                CommentResponse commentResponse = new CommentResponse();
                commentResponse.setCommentID(comment.getCommentId());
                commentResponse.setCommentBody(comment.getCommentBody());
                CommentCreator commentCreator = new CommentCreator();
                commentCreator.setUserID(comment.getCommentCreator().getUserID());
                commentCreator.setName(comment.getCommentCreator().getName());
                commentResponse.setCommentCreator(commentCreator);
                commentResponses.add(commentResponse);
            }
            PostResponse postResponse = new PostResponse();
            postResponse.setPostID(post.getPostId());
            postResponse.setPostBody(post.getPostBody());
            postResponse.setDate(new java.sql.Date(post.getDate().getTime()));
            postResponse.setComments(commentResponses);
            postResponses.add(postResponse);
        }
        return ResponseEntity.ok(postResponses);
    }
}
