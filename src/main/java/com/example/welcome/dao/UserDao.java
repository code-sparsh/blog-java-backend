package com.example.welcome.dao;
import com.example.welcome.model.User;

import java.util.List;
import java.util.UUID;


public interface UserDao {

    void insertUser(UUID id, User user);

    default void insertUser(User user) {
        UUID id = UUID.randomUUID();
        insertUser(id, user);
    }

    List<User> getAll();

}
