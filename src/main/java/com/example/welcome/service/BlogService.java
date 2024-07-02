package com.example.welcome.service;

import com.example.welcome.model.Blog;
import com.example.welcome.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private AwsS3Service s3Service;

    @Value("${aws.s3.baseURL}")
    private String baseURL;

    public Blog createBlog(Blog blog, MultipartFile image) throws IOException {

        File tempFile = File.createTempFile(UUID.randomUUID().toString(), image.getOriginalFilename());

        System.out.println("Original File name: " + image.getOriginalFilename());
        image.transferTo(tempFile);

        String imageURL = s3Service.uploadFile(tempFile);
        blog.setImageURL(imageURL);

        return blogRepo.save(blog);
    }

    public Optional<Blog> getBlogByID(UUID id) {
        Blog blog = blogRepo.findById(id).orElse(null);
        if(blog == null) {
            return null;
        }

        blog.setImageURL(baseURL + blog.getImageURL());
        return Optional.of(blog);
    }

    public List<Blog> getAllBlogs() {

        List<Blog> blogs = blogRepo.findAll();

        for(Blog blog : blogs) {
            blog.setImageURL(baseURL + blog.getImageURL());
        }
        return blogs;
    }

    public boolean deleteBlog(UUID id, String username) {

        Blog blog = blogRepo.findByIdAndAuthor(id, username).orElse(null);


        if (blog == null) {
            return false;
        }

        blogRepo.delete(blog);
        return true;
    }

}
