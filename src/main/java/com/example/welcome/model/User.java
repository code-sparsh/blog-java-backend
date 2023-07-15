package com.example.welcome.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;


public class User {

    private UUID id;
    private String email;
    private String password;

    public User(UUID id, @JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
