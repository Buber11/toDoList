package com.example.toDoList.Auth;

import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.reuqest.SignUpReuqest;
import com.example.toDoList.payload.reuqest.LoginRequest;
import com.example.toDoList.payload.response.UserInfoResponse;

public interface AuthenticationService {
    JwtTokenInfoResponse authenticate(LoginRequest input);
    UserInfoResponse signup(SignUpReuqest signUpDTO);

}
