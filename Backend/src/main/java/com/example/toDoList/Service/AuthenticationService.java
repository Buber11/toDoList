package com.example.toDoList.Service;

import com.example.toDoList.DTO.LoginUserDto;
import com.example.toDoList.DTO.UserDTO.SignUPAnswerDto;
import com.example.toDoList.DTO.UserDTO.SignUpDTO;
import com.example.toDoList.Entities.User;

import java.util.Optional;

public interface AuthenticationService {
    Optional<User> authenticate(LoginUserDto input);
    SignUPAnswerDto signup(SignUpDTO signUpDTO);

}
