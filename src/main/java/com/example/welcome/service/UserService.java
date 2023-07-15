package com.example.welcome.service;

import com.example.welcome.dao.UserDao;
import com.example.welcome.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("fake") UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> showAllPerson() {
        return userDao.getAll();
    }


    public void addUser(User user) {
        userDao.insertUser(user);
    }


}
