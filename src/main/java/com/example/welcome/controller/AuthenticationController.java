package com.example.welcome.controller;

import com.example.welcome.dto.AuthenticationResponseDto;
import com.example.welcome.exception.AuthException;
import com.example.welcome.model.User;
import com.example.welcome.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin
@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws AuthException {
        AuthenticationResponseDto authResponse;

        try {
            authResponse = authService.register(user);
        } catch (AuthException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(e.getResponseCode()).body(response);
        }
        return ResponseEntity.status(200).body(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws AuthException {
        AuthenticationResponseDto authResponse;

        try {
            authResponse = authService.login(user);
        } catch (AuthException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(e.getResponseCode()).body(response);
        }
        return ResponseEntity.status(200).body(authResponse);
    }
}
