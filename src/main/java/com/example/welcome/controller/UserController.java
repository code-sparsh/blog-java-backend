package com.example.welcome.controller;

import com.example.welcome.model.User;
import com.example.welcome.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> showAllPerson() {
        return userService.showAllPerson();
    }

    @PostMapping
    public void addPerson(@RequestBody User user) {
        userService.addUser(user);

    }



}