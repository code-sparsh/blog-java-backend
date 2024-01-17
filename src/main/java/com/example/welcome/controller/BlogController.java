package com.example.welcome.controller;

import com.example.welcome.model.Blog;
import com.example.welcome.service.BlogService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequestMapping("/api/blog")
@CrossOrigin
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @CrossOrigin
    @GetMapping
    public @JsonProperty ResponseEntity<?> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.status(200).body(blogs);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogByID(@PathVariable("id") UUID id) {
        Optional<Blog> blog = blogService.getBlogByID(id);
        return ResponseEntity.status(200).body(blog);
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<?> createBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.status(200).body(blog);
    }
}
