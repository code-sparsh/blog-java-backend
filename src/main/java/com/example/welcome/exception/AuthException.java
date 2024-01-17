package com.example.welcome.exception;

public class AuthException extends Exception {


    private int responseCode = 401;
    AuthException() {
        super();
    }

    public AuthException(String message, int responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
