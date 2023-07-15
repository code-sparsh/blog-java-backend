package com.example.welcome.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;


public class Person {

    private UUID id;
    private String userName;
    private String password;

    public Person(UUID id, @JsonProperty("userName") String userName, @JsonProperty("password") String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
