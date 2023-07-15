package com.example.welcome.dao;

import com.example.welcome.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fake")
public class FakeUserDataAccessService implements UserDao {

    List<User> DB = new ArrayList<>();

    @Override
    public void insertUser(UUID id, User user) {
        DB.add(new User(id, user.getEmail(), user.getPassword()));
    }

    @Override
    public List<User> getAll() {
        return DB;
    }
}
