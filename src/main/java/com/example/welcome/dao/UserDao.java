package com.example.welcome.dao;
import com.example.welcome.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDao extends CrudRepository<User, UUID> {

    public Optional<User> findByEmail(String email);
}
