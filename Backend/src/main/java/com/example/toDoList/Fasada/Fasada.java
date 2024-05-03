package com.example.toDoList.Fasada;


import com.example.toDoList.Auth.AuthenticationService;
import com.example.toDoList.Auth.commands.LoginUserCommand;
import com.example.toDoList.Auth.commands.LogoutUserCommand;
import com.example.toDoList.Auth.commands.RefreshTokenCommand;
import com.example.toDoList.User.UserBusinessLogic.Command.GetUserCommand;
import com.example.toDoList.User.UserBusinessLogic.Command.UpdateUserCommand;
import com.example.toDoList.User.UserBusinessLogic.Command.UserDeleteCommand;
import com.example.toDoList.User.UserBusinessLogic.UserService;
import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.Auth.commands.SignUpUserCommand;

import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.task.BusinessLogic.TaskService;
import com.example.toDoList.task.BusinessLogic.command.GetTasksCommand;

import java.util.List;

/*
    all services of entities in one place
 */

public class Fasada {

//    private UserUpdateDataServiceImpl userService;

    private final AuthenticationService authenticationService;
    private  UserService userService;

    private TaskService taskService;

    public Fasada(AuthenticationService authenticationService, UserService userService, TaskService taskService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.taskService = taskService;
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

    public UserUpdateResponse handle(
            UpdateUserCommand command
    ){
        return command.execute(userService);
    }

    public UserInfoResponse handle(
            GetUserCommand command
    ){
        return command.execute(userService);
    }

    public List<TaskResponse> handle(
            GetTasksCommand command
    ){
        return command.execute(taskService);
    }


}
