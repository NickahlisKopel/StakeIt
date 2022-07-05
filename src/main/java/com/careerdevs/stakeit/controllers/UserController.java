package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.UserRepository;
import com.careerdevs.stakeit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User newUser){
        User user = userRepository.save(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByName(@PathVariable String username){
        User user = userRepository.findByName(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
