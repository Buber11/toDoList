package com.example.toDoList.Auth.AuthBusinessLogic.commands;

import com.example.toDoList.Auth.AuthBusinessLogic.AuthenticationService;
import com.example.toDoList.Fasada.Command;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;


public class RefreshTokenCommand implements Command<Boolean, AuthenticationService> {

    private final String token;
    private final HttpServletResponse response;

    private RefreshTokenCommand(String token, HttpServletResponse response) {
        this.token = token;
        this.response = response;
    }

    public static RefreshTokenCommand from(String token, HttpServletResponse response){
        return new RefreshTokenCommand(token, response);
    }

    @Override
    public Boolean execute(AuthenticationService authenticationService) {
        return authenticationService.refreshToken(token,response);
    }
}
