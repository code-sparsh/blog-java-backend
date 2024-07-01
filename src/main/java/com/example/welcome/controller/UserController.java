package com.example.welcome.controller;

import com.example.welcome.dto.UserProfileDto;
import com.example.welcome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        UserProfileDto profile = userService.getUserProfile(username).orElse(null);

        if (profile == null) {
            return ResponseEntity.status(404).body("Profile not found");
        }

        return ResponseEntity.status(200).body(profile);


    }
}
