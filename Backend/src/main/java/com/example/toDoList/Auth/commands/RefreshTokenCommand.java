package com.example.toDoList.Auth.commands;

import com.example.toDoList.Auth.AuthenticationService;
import com.example.toDoList.Fasada.Command;
import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;


public class RefreshTokenCommand implements Command<JwtTokenInfoResponse, AuthenticationService> {

    private final String authorizationHeader;

    private RefreshTokenCommand(String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
    }
    public static RefreshTokenCommand from(String authorizationHeader){
        return new RefreshTokenCommand(authorizationHeader);
    }

    @Override
    public JwtTokenInfoResponse execute(AuthenticationService authenticationService) {
        return authenticationService.refreshToken(authorizationHeader);
    }
}
