package com.example.welcome.service;

import com.example.welcome.dao.BlogDao;
import com.example.welcome.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {

    @Autowired
    private BlogDao blogDao;

    public Blog createBlog(Blog blog) {
        return blogDao.save(blog);
    }

    public Optional<Blog> getBlogByID(UUID id) {
        Optional<Blog> storedBlog = blogDao.findById(id);
        return storedBlog;
    }

    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogDao.findAll();
    }

}
