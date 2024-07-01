package com.example.welcome.controller;

import com.example.welcome.model.Blog;
import com.example.welcome.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> createBlog(@RequestBody Blog blog, HttpServletRequest request) {
        blog.setAuthor((String) request.getAttribute("username"));
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.status(200).body(blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable UUID id, HttpServletRequest request) {

//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String username = userDetails.getUsername();

        String username = (String) request.getAttribute("username");

        boolean result = blogService.deleteBlog(id, username);

        if(!result) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(null);
    }

}
