package com.example.welcome.controller;

import com.example.welcome.exception.AuthException;
import com.example.welcome.model.User;
import com.example.welcome.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin
    @PostMapping("/signup")
    public @JsonProperty ResponseEntity<?> signup(@RequestBody User user) {
        User signupUser;
        try {
            signupUser = userService.signup(user);
        } catch (AuthException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(signupUser);
    }

    @CrossOrigin
    @PostMapping("/login")
    public @JsonProperty ResponseEntity<?> login(@RequestBody User user) {

        User loginUser;
        try {
            loginUser = userService.login(user);
        } catch (AuthException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.status(200).body(loginUser);
    }


}