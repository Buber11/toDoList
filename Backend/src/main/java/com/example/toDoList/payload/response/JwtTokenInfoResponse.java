package com.example.toDoList.payload.response;

public class JwtTokenInfoResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

    public JwtTokenInfoResponse setToken(String theToken) {
        token = theToken;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public JwtTokenInfoResponse setExpiresIn(long theExpiresIn) {
        expiresIn = theExpiresIn;
        return this;
    }


}
