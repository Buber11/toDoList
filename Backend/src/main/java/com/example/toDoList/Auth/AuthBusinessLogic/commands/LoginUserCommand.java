package com.example.toDoList.Auth.AuthBusinessLogic.commands;

import com.example.toDoList.Auth.AuthBusinessLogic.AuthenticationService;
import com.example.toDoList.Fasada.Command;
import com.example.toDoList.payload.request.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginUserCommand implements Command<Boolean, AuthenticationService> {

    private final LoginRequest request;
    private final HttpServletResponse response;

    private LoginUserCommand(LoginRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    public static LoginUserCommand from(LoginRequest request, HttpServletResponse response){
        return new LoginUserCommand(request,response);
    }

    @Override
    public Boolean execute(AuthenticationService authenticationService) {
        return authenticationService.authenticate(request,response);
    }
}
