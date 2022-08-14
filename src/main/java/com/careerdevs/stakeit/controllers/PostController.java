package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.PostRepository;
import com.careerdevs.stakeit.Repositories.ProfileRepository;
import com.careerdevs.stakeit.models.Post;
import com.careerdevs.stakeit.models.Profile;
import com.careerdevs.stakeit.utils.ApiErrorHandling;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
        try {
            Profile profile = profileRepository.findById(userId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );

            newPost.setProfile(profile);
            profile.setKarma(profile.getKarma() + 1);

            Post post = postRepository.save(newPost);
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPosts (){
        try {
            List<Post> posts = postRepository.findAll();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOnePost(@PathVariable Long id){
        try {
            Optional<Post> post = postRepository.findById(id);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPostsByProfileId(@PathVariable Long userId){
        try {
            List<Post> posts = postRepository.findAllByProfile_id(userId);

            return new ResponseEntity<>(posts, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/user/name/{userName}")
    public ResponseEntity<?> getPostsByUserName(@PathVariable String userName){
        try {
            List<Post> posts = postRepository.findAllByProfile_name(userName);

            return new ResponseEntity<>(posts, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable Long id){
        try{
            Optional<Post> foundPost = postRepository.findById(id);

            postRepository.deleteById(id);

            return new ResponseEntity<>(foundPost, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

}
