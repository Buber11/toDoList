package com.example.toDoList.Auth.AuthBusinessLogic.commands;

import com.example.toDoList.Auth.AuthBusinessLogic.AuthenticationService;
import com.example.toDoList.Fasada.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutUserCommand implements Command<Boolean, AuthenticationService> {

    private final String token;
    private final HttpServletRequest request;

    private LogoutUserCommand(String token, HttpServletRequest request) {
        this.token = token;
        this.request = request;
    }

    public static LogoutUserCommand from(String token, HttpServletRequest request){
        return new LogoutUserCommand(token,request);
    }

    @Override
    public Boolean execute(AuthenticationService authenticationService) {
        return authenticationService.logout(token,request);
    }

}
