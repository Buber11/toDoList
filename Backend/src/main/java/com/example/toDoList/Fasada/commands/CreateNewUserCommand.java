package com.example.toDoList.Fasada.commands;

import com.example.toDoList.DTO.UserDTO.SignUPAnswerDto;
import com.example.toDoList.DTO.UserDTO.SignUpDTO;
import com.example.toDoList.Service.AuthenticationService;


public class CreateNewUserCommand implements Command<SignUPAnswerDto, AuthenticationService> {

    private  SignUpDTO signUpDTO;
    private CreateNewUserCommand(SignUpDTO theSignUpDTO){
        signUpDTO = theSignUpDTO;
    }
    public static CreateNewUserCommand from(SignUpDTO signUpDTO){
        return new CreateNewUserCommand(signUpDTO);
    }
    @Override
    public SignUPAnswerDto execute(AuthenticationService authenticationService) {
        return authenticationService.signup(signUpDTO);
    }
}
