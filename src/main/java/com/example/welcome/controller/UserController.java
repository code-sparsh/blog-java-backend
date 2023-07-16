package com.example.welcome.controller;

import com.example.welcome.exception.AuthException;
import com.example.welcome.model.User;
import com.example.welcome.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        User signupUser;
        try {
            signupUser = userService.signup(user);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(signupUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User loginUser;
        try {
            loginUser = userService.login(user);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

        return ResponseEntity.status(200).body(loginUser);


    }


}