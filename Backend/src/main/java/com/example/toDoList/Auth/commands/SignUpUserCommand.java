package com.example.toDoList.Auth.commands;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.payload.request.SignUpRequest;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.Auth.AuthenticationService;


public class SignUpUserCommand implements Command<UserInfoResponse, AuthenticationService> {

    private SignUpRequest signUpDTO;
    private SignUpUserCommand(SignUpRequest theSignUpDTO){
        signUpDTO = theSignUpDTO;
    }
    public static SignUpUserCommand from(SignUpRequest signUpDTO){
        return new SignUpUserCommand(signUpDTO);
    }
    @Override
    public UserInfoResponse execute(AuthenticationService authenticationService) {
        return authenticationService.signup(signUpDTO);
    }
}
