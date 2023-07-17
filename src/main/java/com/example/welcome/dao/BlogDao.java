package com.example.welcome.dao;

import com.example.welcome.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogDao extends CrudRepository<Blog, Integer> {

    public Optional<Blog> findById(UUID id);

}
