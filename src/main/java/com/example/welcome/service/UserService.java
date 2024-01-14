package com.example.welcome.service;

import com.example.welcome.exception.AuthException;
import com.example.welcome.model.User;
import com.example.welcome.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public User signup(User user) throws AuthException {

        Optional<User> storedUser = userRepo.findByEmail(user.getEmail());
        if (storedUser.isPresent())
            throw new AuthException("Email ID already exists");

        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);

        return userRepo.save(user);
    }

    public User login(User user) throws AuthException {
        Optional<User> storedUser = userRepo.findByEmail(user.getEmail());

        // if ID not found in the database
        if (storedUser.isEmpty())
            throw new AuthException("User not found in the database");

        boolean match = passwordEncoder.matches(user.getPassword(), storedUser.get().getPassword());
        // if password incorrect
        if (!match)
            throw new AuthException("Incorrect Password");

        user.setId(storedUser.get().getId());
        storedUser.get().setPassword(null);
        return storedUser.get();

    }

}
