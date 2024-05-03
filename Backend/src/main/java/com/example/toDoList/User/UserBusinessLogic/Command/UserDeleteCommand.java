package com.example.toDoList.User.UserBusinessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.User.UserBusinessLogic.UserService;
import com.example.toDoList.payload.request.DeleteUserRequest;


public class UserDeleteCommand implements Command<Boolean, UserService> {

    private String authorizationHeader;
    private DeleteUserRequest reuqest;

    private UserDeleteCommand(String authorizationHeader, DeleteUserRequest reuqest){
        this.authorizationHeader = authorizationHeader;
        this.reuqest = reuqest;
    }
    public static UserDeleteCommand from(String authorizationHeader, DeleteUserRequest reuqest){
        return new UserDeleteCommand(authorizationHeader, reuqest);
    }

    @Override
    public Boolean execute(UserService userService) {
        return userService.deleteUser(authorizationHeader, reuqest);
    }
}
