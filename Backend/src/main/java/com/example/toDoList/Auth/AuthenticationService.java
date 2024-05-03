package com.example.toDoList.Auth;

import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.request.SignUpRequest;
import com.example.toDoList.payload.request.LoginRequest;
import com.example.toDoList.payload.response.UserInfoResponse;

public interface AuthenticationService {
    JwtTokenInfoResponse authenticate(LoginRequest input);
    UserInfoResponse signup(SignUpRequest signUpDTO);

    boolean logout(String authorizationHeader);

    JwtTokenInfoResponse refreshToken(String authorizationHeader);

}
