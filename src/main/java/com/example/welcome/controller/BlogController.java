package com.example.welcome.controller;

import com.example.welcome.model.Blog;
import com.example.welcome.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequestMapping("/api")
@CrossOrigin
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/public/blog")
    public ResponseEntity<?> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.status(200).body(blogs);
    }

    @GetMapping("/public/blog/{id}")
    public ResponseEntity<?> getBlogByID(@PathVariable("id") UUID id) {
        Optional<Blog> blog = blogService.getBlogByID(id);
        return ResponseEntity.status(200).body(blog);
    }

    @PostMapping(value = "/blog/create")
    public ResponseEntity<?> createBlog(@RequestPart(value = "title") String title,
                                        @RequestPart(value = "data") String data,
                                        @RequestPart(value = "image") MultipartFile file,
                                        HttpServletRequest request) throws IOException {

        Blog blog = new Blog(title, data, null);

        blog.setAuthor((String) request.getAttribute("username"));
        Blog createdBlog = blogService.createBlog(blog, file);

        if(createdBlog == null) {
            return ResponseEntity.status(400).body("Error creating blog");
        }
        return ResponseEntity.status(200).body(blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable UUID id, HttpServletRequest request) {

        String username = (String) request.getAttribute("username");
        boolean result = blogService.deleteBlog(id, username);

        if(!result) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(null);
    }

}
