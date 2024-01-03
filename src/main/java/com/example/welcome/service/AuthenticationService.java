package com.example.welcome.service;

import com.example.welcome.auth.AuthenticationResponseDto;
import com.example.welcome.exception.AuthException;
import com.example.welcome.model.User;
import com.example.welcome.repo.UserRepo;
import com.example.welcome.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public AuthenticationResponseDto register(User user) throws AuthException {

        Optional<User> storedUser = userRepo.findByEmail(user.getEmail());
        if (storedUser.isPresent())
            throw new AuthException("Email ID already exists");

        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        userRepo.save(user);

        String token = jwtUtil.generateToken(user);
        return AuthenticationResponseDto.builder().token(token).build();
    }

    public AuthenticationResponseDto login(User user) throws AuthException {

        Optional<User> storedUser = userRepo.findByEmail(user.getEmail());

        // if ID not found in the database
        if (storedUser.isEmpty())
            throw new AuthException("User not found");

        boolean match = passwordEncoder.matches(user.getPassword(), storedUser.get().getPassword());

        // if password incorrect
        if (!match)
            throw new AuthException("Incorrect Password");


        String token = jwtUtil.generateToken(user);
        return AuthenticationResponseDto.builder().token(token).build();
    }
}
