package com.example.welcome.service;

import com.example.welcome.model.Blog;
import com.example.welcome.repo.BlogRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlogServiceTests {

    @Mock
    private BlogRepo blogRepo;

    @InjectMocks
    BlogService blogService;

    @Test
    public void Should_Return_Saved_Blog_On_Creating_New() {

        Blog blog = new Blog("Test Title", "Test Content", "Sparsh");

        blog.setId(UUID.randomUUID());
        when(blogRepo.save(blog)).thenReturn(blog);

        Blog savedBlog = blogService.createBlog(blog);

        assertThat(savedBlog).isNotNull();
        assertThat(savedBlog.getId()).isEqualTo(blog.getId());

    }

    @Test
    public void Should_Return_One_Blog_On_Find_By_Id() {

        Blog blog = new Blog("Test Title", "Test Content", "Sparsh");

        blog.setId(UUID.randomUUID());
        when(blogRepo.findById(blog.getId())).thenReturn(Optional.of(blog));

        Blog foundBlog = blogService.getBlogByID(blog.getId()).orElse(null);

        assertThat(foundBlog).isNotNull();
        assertThat(foundBlog.getId()).isEqualTo(blog.getId());
    }

    @Test
    public void Should_Return_List_Of_Blogs_On_Find_All() {

        Blog blog1 = new Blog("Test Title #1", "Test Content #1", "Sparsh");
        Blog blog2 = new Blog("Test Title #2", "Test Content #2", "John");

        blog1.setId(UUID.randomUUID());
        blog2.setId(UUID.randomUUID());
        when(blogRepo.findAll()).thenReturn(List.of(blog1, blog2));

        List<Blog> foundBlogs = blogService.getAllBlogs();

        assertThat(foundBlogs).isNotNull();
        assertThat(foundBlogs).isInstanceOf(List.class);
        assertThat(foundBlogs).contains(blog1);
        assertThat(foundBlogs).contains(blog2);
    }

}
