package com.example.welcome.service;

import com.example.welcome.dto.AuthenticationResponseDto;
import com.example.welcome.exception.AuthException;
import com.example.welcome.model.User;
import com.example.welcome.repo.UserRepo;
import com.example.welcome.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
            throw new AuthException("EMAIL_OCCUPIED", 409);

        storedUser = userRepo.findByUsername(user.getUsername());
        if (storedUser.isPresent())
            throw new AuthException("USERNAME_OCCUPIED", 409);


        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        boolean isUsernameReady = false;

        while(!isUsernameReady) {
            Random rand = new Random();
            String username = user.getName().toLowerCase().replaceAll("\\s","") + rand.nextInt(100);

            if(userRepo.findByUsername(username).isEmpty()) {
                user.setUsername(username);
                isUsernameReady = true;
            }
        }

        userRepo.save(user);

        String token = jwtUtil.generateToken(user);
        return AuthenticationResponseDto.builder().token(token).username(user.getUsername()).build();
    }

    public AuthenticationResponseDto login(User user) throws AuthException {

        Optional<User> storedUser = userRepo.findByEmail(user.getEmail());

        // if ID not found in the database
        if (storedUser.isEmpty())
            throw new AuthException("User not found", 404);

        boolean match = passwordEncoder.matches(user.getPassword(), storedUser.get().getPassword());

        // if password incorrect
        if (!match)
            throw new AuthException("Incorrect Password", 401);


        String token = jwtUtil.generateToken(storedUser.get());
        return AuthenticationResponseDto.builder().token(token).username(storedUser.get().getUsername()).build();
    }
}
