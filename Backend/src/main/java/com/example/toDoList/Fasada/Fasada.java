package com.example.toDoList.Fasada;


import com.example.toDoList.Auth.AuthBusinessLogic.AuthenticationService;
import com.example.toDoList.Auth.AuthBusinessLogic.commands.LoginUserCommand;
import com.example.toDoList.Auth.AuthBusinessLogic.commands.LogoutUserCommand;
import com.example.toDoList.Auth.AuthBusinessLogic.commands.RefreshTokenCommand;
import com.example.toDoList.List.businessLogic.Command.AddListCommand;
import com.example.toDoList.List.businessLogic.Command.DeleteListCommand;
import com.example.toDoList.List.businessLogic.Command.GetListCommand;
import com.example.toDoList.List.businessLogic.Command.UpDateTitleListCommand;
import com.example.toDoList.List.businessLogic.ListService;
import com.example.toDoList.User.UserBusinessLogic.Command.GetUserCommand;
import com.example.toDoList.User.UserBusinessLogic.Command.UpdateUserCommand;
import com.example.toDoList.User.UserBusinessLogic.Command.DeleteUserCommand;
import com.example.toDoList.User.UserBusinessLogic.UserService;
import com.example.toDoList.payload.response.*;
import com.example.toDoList.Auth.AuthBusinessLogic.commands.SignUpUserCommand;

import com.example.toDoList.task.BusinessLogic.TaskService;
import com.example.toDoList.task.BusinessLogic.command.AddTaskCommand;
import com.example.toDoList.task.BusinessLogic.command.CompleteTaskCommand;
import com.example.toDoList.task.BusinessLogic.command.DeleteTaskCommand;

import java.util.List;

/*
    all services of entities in one place
 */

public class Fasada {

//    private UserUpdateDataServiceImpl userService;

    private final AuthenticationService authenticationService;
    private  UserService userService;

    private TaskService taskService;

    private ListService listService;

    public Fasada(AuthenticationService authenticationService, UserService userService, TaskService taskService, ListService listService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.taskService = taskService;
        this.listService = listService;
    }

    public UserInfoResponse handle(
            SignUpUserCommand command
    ){
        return command.execute(authenticationService);
    }

    public Boolean handle(
            LoginUserCommand command
    ){
        return command.execute(authenticationService);
    }

    public boolean handle(
            LogoutUserCommand command
    ){
        return command.execute(authenticationService);
    }

    public boolean handle(
            RefreshTokenCommand command
    ){
        return command.execute(authenticationService);
    }

    public void handle(
            DeleteUserCommand command
    ){
        command.execute(userService);
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

    public TaskResponse handle(
            AddTaskCommand command
    ){
        return command.execute(taskService);
    }
    public Boolean handle(
            DeleteTaskCommand command
    ){
        return command.execute(taskService);
    }
    public ListResponse handle(
            GetListCommand command
    ){
        return command.execute(listService);
    }
    public Boolean handle(
            DeleteListCommand command
    ){
        return command.execute(listService);
    }
    public ListIdResponce handle(
            AddListCommand command
    ){
       return command.execute(listService);
    }

    public boolean handle(
            UpDateTitleListCommand command
    ){
        return command.execute(listService);
    }
    public boolean handle(
            CompleteTaskCommand command
    ){
        return command.execute(taskService);
    }
}
