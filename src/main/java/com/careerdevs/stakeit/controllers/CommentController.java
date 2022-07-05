package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.CommentRepository;
import com.careerdevs.stakeit.Repositories.PostRepository;
import com.careerdevs.stakeit.Repositories.UserRepository;
import com.careerdevs.stakeit.models.Comment;
import com.careerdevs.stakeit.models.Post;
import com.careerdevs.stakeit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments/")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<?> createComment (@PathVariable Long postId,@PathVariable Long userId ,@RequestBody Comment newComment){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        newComment.setPost(post);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        newComment.setUser(user);

        Comment comment = commentRepository.save(newComment);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllComments (){
        List<Comment> comments = commentRepository.findAll();
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneComment(@PathVariable Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCommentsByUser(@PathVariable Long userId){
        List<Comment> comments = commentRepository.findAllByUser_id(userId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/user/name/{userName}")
    public ResponseEntity<?> getCommentsByUserName(@PathVariable String userName){
        List<Comment> comments = commentRepository.findAllByUser_name(userName);
        return new ResponseEntity<>(comments, HttpStatus.OK);

    }
}
