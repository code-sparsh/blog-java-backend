package com.example.welcome.controller;

import com.example.welcome.model.Blog;
import com.example.welcome.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;


    @GetMapping
    public ResponseEntity<?> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.status(200).body(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogByID(@PathVariable("id") UUID id) {
        Optional<Blog> blog = blogService.getBlogByID(id);
        return ResponseEntity.status(200).body(blog);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.status(200).body(blog);
    }
}
