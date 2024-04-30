package com.example.toDoList.Fasada;

import com.example.toDoList.DTO.UserDTO.SignUPAnswerDto;
import com.example.toDoList.Fasada.commands.CreateNewUserCommand;
import com.example.toDoList.Service.AuthenticationService;
import com.example.toDoList.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/*
    all services of entities in one place
 */

public class Fasada {

    private UserServiceImpl userService;

    private final AuthenticationService authenticationService;

    public Fasada(AuthenticationService theAuthenticationService) {
        authenticationService = theAuthenticationService;
    }

    public SignUPAnswerDto handle(CreateNewUserCommand command){
        return command.execute(authenticationService);
    }

    @Autowired
    @Lazy
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

}
