package com.example.toDoList.Auth.AuthBusinessLogic;

import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.request.SignUpRequest;
import com.example.toDoList.payload.request.LoginRequest;
import com.example.toDoList.payload.response.UserInfoResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    Boolean authenticate(LoginRequest input, HttpServletResponse response);
    UserInfoResponse signup(SignUpRequest signUpDTO);

    boolean logout(String token, HttpServletRequest request);

    boolean refreshToken(String token, HttpServletResponse response);

}
