package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.PostRepository;
import com.careerdevs.stakeit.Repositories.ProfileRepository;
import com.careerdevs.stakeit.models.Post;
import com.careerdevs.stakeit.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@Transactional
@RequestMapping("/api/posts/")
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    ProfileRepository profileRepository;

    @PostMapping("/{userId}")
    public ResponseEntity<?> createPost (@PathVariable Long userId,@RequestBody Post newPost){
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        newPost.setProfile(profile);
        profile.setKarma(profile.getKarma()+1);

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
    public ResponseEntity<?> getPostsByProfileId(@PathVariable Long userId){
        List<Post> posts = postRepository.findAllByProfile_id(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/user/name/{userName}")
    public ResponseEntity<?> getNotesByListenerName(@PathVariable String userName){
        List<Post> posts = postRepository.findAllByProfile_name(userName);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
