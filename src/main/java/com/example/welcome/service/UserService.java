package com.example.welcome.service;

import com.example.welcome.dto.UserProfileDto;
import com.example.welcome.model.Blog;
import com.example.welcome.model.User;
import com.example.welcome.repo.BlogRepo;
import com.example.welcome.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Value("${aws.s3.baseURL}")
    private String baseS3URL;


    public Optional<UserProfileDto> getUserProfile(String username) {
        User user = userRepo.findByUsername(username).orElse(null);

        if (user == null) {
            return null;
        }

        List<Blog> blogs = blogRepo.findByAuthor(username);

        for(Blog blog : blogs) {
            blog.setData("");
            blog.setImageURL(baseS3URL + blog.getImageURL());
        }

//        List<String> blogs = blogRepo.findBlogTitlesByAuthor(username);

        UserProfileDto profile = UserProfileDto.builder().name(user.getName()).username(user.getUsername()).blogs(blogs).build();
        return Optional.ofNullable(profile);
    }

}
