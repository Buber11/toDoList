package com.example.toDoList.DTO.UserDTO;

public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String theToken) {
        token = theToken;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long theExpiresIn) {
        expiresIn = theExpiresIn;
        return this;
    }

    // Getters and setters...
}
