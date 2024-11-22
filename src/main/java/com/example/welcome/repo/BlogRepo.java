package com.example.welcome.repo;

import com.example.welcome.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {

   Optional<Blog> findById(UUID id);

   Optional<Blog> findByIdAndAuthor(UUID id, String username);

   List<Blog> findByAuthor(String author);

   @Query(value = "SELECT b.title from Blog b WHERE b.author = :author")
   List<String> findBlogTitlesByAuthor(String author);
}
