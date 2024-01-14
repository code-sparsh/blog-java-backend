package com.example.welcome.repo;

import com.example.welcome.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {

   Optional<Blog> findById(UUID id);

}
