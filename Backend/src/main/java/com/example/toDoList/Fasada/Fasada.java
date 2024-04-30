package com.example.toDoList.Fasada;


import com.example.toDoList.Auth.AuthenticationService;
import com.example.toDoList.Auth.commands.LoginUserCommand;
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

    public Fasada(AuthenticationService theAuthenticationService) {
        authenticationService = theAuthenticationService;
    }

    public UserInfoResponse handle(SignUpUserCommand command){
        return command.execute(authenticationService);
    }

    public JwtTokenInfoResponse handle(LoginUserCommand command){
        return command.execute(authenticationService);
    }

//    public Boolean handle(DeleteUserCommand command){
//        return command.execute(userService);
//    }
//
//    @Autowired
//    @Lazy
//    public void setUserService(UserUpdateDataServiceImpl userService) {
//        this.userService = userService;
//    }

}
