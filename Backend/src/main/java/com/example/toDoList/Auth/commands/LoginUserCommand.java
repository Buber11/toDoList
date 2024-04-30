package com.example.toDoList.Auth.commands;

import com.example.toDoList.Auth.AuthenticationService;
import com.example.toDoList.Fasada.Command;
import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.reuqest.LoginRequest;

public class LoginUserCommand implements Command<JwtTokenInfoResponse, AuthenticationService> {

    private final LoginRequest request;

    private LoginUserCommand(LoginRequest request) {
        this.request = request;
    }
    public static LoginUserCommand from(LoginRequest request){
        return new LoginUserCommand(request);
    }

    @Override
    public JwtTokenInfoResponse execute(AuthenticationService authenticationService) {
        return authenticationService.authenticate(request);
    }
}
