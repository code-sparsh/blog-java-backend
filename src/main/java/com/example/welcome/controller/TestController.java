package com.example.welcome.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @GetMapping
    public String test() {
        return "Connection to the server is OK";
    }
}
