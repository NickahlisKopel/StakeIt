package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.PostRepository;
import com.careerdevs.stakeit.Repositories.ProfileRepository;
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
@RequestMapping("/api/users/")
public class ProfileController {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody Profile newProfile){
        try {
            Profile profile = profileRepository.save(newProfile);
            return new ResponseEntity<>(profile, HttpStatus.CREATED);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        try {


            List<Profile> profiles = profileRepository.findAll();
            return new ResponseEntity<>(profiles, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){
        try {
            Profile user = profileRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByName(@PathVariable String username){
        try {
            Profile profile = profileRepository.findByName(username);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfileById (@PathVariable Long id){

        try {

            Optional<Profile> foundProfile = profileRepository.findById(id);

            profileRepository.deleteById(id);

            return new ResponseEntity<>(foundProfile, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateProfile(@RequestBody Profile updateProfile){
        try {
            Profile savedProfile = profileRepository.save(updateProfile);
            return new ResponseEntity<>(savedProfile, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(),e.getStatusCode().value());
        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }
}
