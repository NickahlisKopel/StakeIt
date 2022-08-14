package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.CommentRepository;
import com.careerdevs.stakeit.Repositories.PostRepository;
import com.careerdevs.stakeit.Repositories.ProfileRepository;
import com.careerdevs.stakeit.models.Comment;
import com.careerdevs.stakeit.models.Post;
import com.careerdevs.stakeit.models.Profile;
import com.careerdevs.stakeit.utils.ApiErrorHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/comments/")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ProfileRepository profileRepository;

    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<?> createComment (@PathVariable Long postId,@PathVariable Long userId ,@RequestBody Comment newComment){

        try {
            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );

            newComment.setPost(post);

            Profile profile = profileRepository.findById(userId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );

            newComment.setProfile(profile);

            Comment comment = commentRepository.save(newComment);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode().value());

        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllComments (){
        try {
            List<Comment> comments = commentRepository.findAll();
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode().value());

        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneComment(@PathVariable Long id){
        try {
            Optional<Comment> comment = commentRepository.findById(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode().value());

        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCommentsByUser(@PathVariable Long userId){
        try {
            List<Comment> comments = commentRepository.findAllByProfile_id(userId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode().value());

        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/user/name/{userName}")
    public ResponseEntity<?> getCommentsByUserName(@PathVariable String userName){
        try {
            List<Comment> comments = commentRepository.findAllByProfile_name(userName);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode().value());

        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }

    }
}
