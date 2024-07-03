package com.example.welcome.controller;

import com.example.welcome.service.AwsS3Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class TestController {

    @Autowired
    private AwsS3Service s3Service;

    @GetMapping("/public/test")
    public String test(HttpServletRequest request) {
        return "Connection to the server is OK";
    }

//    @GetMapping("/upload")
//    public void upload() {
//        s3Service.uploadFile();
//    }
}
