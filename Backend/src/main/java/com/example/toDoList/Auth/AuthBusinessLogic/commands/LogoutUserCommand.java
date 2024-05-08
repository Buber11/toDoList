package com.example.toDoList.Auth.AuthBusinessLogic.commands;

import com.example.toDoList.Auth.AuthBusinessLogic.AuthenticationService;
import com.example.toDoList.Fasada.Command;

public class LogoutUserCommand implements Command<Boolean, AuthenticationService> {

    String authorizationHeader;

    private LogoutUserCommand(String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
    }

    public static LogoutUserCommand from(String authorizationHeader){
        return new LogoutUserCommand(authorizationHeader);
    }

    @Override
    public Boolean execute(AuthenticationService authenticationService) {
        return authenticationService.logout(authorizationHeader);
    }

}
