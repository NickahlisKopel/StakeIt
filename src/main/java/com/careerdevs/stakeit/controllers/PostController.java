package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.PostRepository;
import com.careerdevs.stakeit.Repositories.UserRepository;
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
@RequestMapping("/api/posts/")
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/{userId}")
    public ResponseEntity<?> createPost (@PathVariable Long userId,@RequestBody Post newPost){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        newPost.setUser(user);

        Post post = postRepository.save(newPost);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPosts (){
        List<Post> posts = postRepository.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOnePost(@PathVariable Long id){
        Optional<Post> post = postRepository.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getNotesByListenerId(@PathVariable Long userId){
        List<Post> posts = postRepository.findAllByUser_id(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/user/name/{userName}")
    public ResponseEntity<?> getNotesByListenerName(@PathVariable String userName){
        List<Post> posts = postRepository.findAllByUser_name(userName);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
