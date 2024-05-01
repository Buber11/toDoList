package com.example.toDoList.Fasada;


import com.example.toDoList.Auth.AuthenticationService;
import com.example.toDoList.Auth.commands.LoginUserCommand;
import com.example.toDoList.Auth.commands.LogoutUserCommand;
import com.example.toDoList.Auth.commands.RefreshTokenCommand;
import com.example.toDoList.UserAccount.Command.UserDeleteCommand;
import com.example.toDoList.UserAccount.UserService;
import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.Auth.commands.SignUpUserCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/*
    all services of entities in one place
 */

public class Fasada {

//    private UserUpdateDataServiceImpl userService;

    private final AuthenticationService authenticationService;
    private  UserService userService;

    public Fasada(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    public UserInfoResponse handle(
            SignUpUserCommand command
    ){
        return command.execute(authenticationService);
    }

    public JwtTokenInfoResponse handle(
            LoginUserCommand command
    ){
        return command.execute(authenticationService);
    }

    public boolean handle(
            LogoutUserCommand command
    ){
        return command.execute(authenticationService);
    }

    public JwtTokenInfoResponse handle(
            RefreshTokenCommand command
    ){
        return command.execute(authenticationService);
    }

    public boolean handle(
            UserDeleteCommand command
    ){
        return command.execute(userService);
    }




}
