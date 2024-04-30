package com.example.toDoList.Auth.commands;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.payload.reuqest.SignUpReuqest;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.Auth.AuthenticationService;


public class SignUpUserCommand implements Command<UserInfoResponse, AuthenticationService> {

    private SignUpReuqest signUpDTO;
    private SignUpUserCommand(SignUpReuqest theSignUpDTO){
        signUpDTO = theSignUpDTO;
    }
    public static SignUpUserCommand from(SignUpReuqest signUpDTO){
        return new SignUpUserCommand(signUpDTO);
    }
    @Override
    public UserInfoResponse execute(AuthenticationService authenticationService) {
        return authenticationService.signup(signUpDTO);
    }
}
