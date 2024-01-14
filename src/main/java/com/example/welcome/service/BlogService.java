package com.example.welcome.service;

import com.example.welcome.model.Blog;
import com.example.welcome.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {

    @Autowired
    private BlogRepo blogRepo;

    public Blog createBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    public Optional<Blog> getBlogByID(UUID id) {
        return blogRepo.findById(id);
    }

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

}
