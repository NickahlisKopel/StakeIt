package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.PostRepository;
import com.careerdevs.stakeit.Repositories.ProfileRepository;
import com.careerdevs.stakeit.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        Profile profile = profileRepository.save(newProfile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        List<Profile> profiles = profileRepository.findAll();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){
        Profile user = profileRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByName(@PathVariable String username){
        Profile profile = profileRepository.findByName(username);
        return new ResponseEntity<>(profile,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfileById (@PathVariable Long id){

        Optional<Profile> foundProfile = profileRepository.findById(id);

        profileRepository.deleteById(id);

        return new ResponseEntity<>(foundProfile, HttpStatus.OK);
    }
    @PutMapping("/")
    public ResponseEntity<?> updateProfile(@RequestBody Profile updateProfile){
        Profile savedProfile = profileRepository.save(updateProfile);
        return new ResponseEntity<>(savedProfile, HttpStatus.OK);
    }
}
