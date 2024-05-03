package com.example.toDoList.User.UserBusinessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.User.UserBusinessLogic.UserService;
import com.example.toDoList.payload.request.DeleteUserRequest;


public class DeleteUserCommand implements Command<Boolean, UserService> {

    private String authorizationHeader;
    private DeleteUserRequest reuqest;

    private DeleteUserCommand(String authorizationHeader, DeleteUserRequest reuqest){
        this.authorizationHeader = authorizationHeader;
        this.reuqest = reuqest;
    }
    public static DeleteUserCommand from(String authorizationHeader, DeleteUserRequest reuqest){
        return new DeleteUserCommand(authorizationHeader, reuqest);
    }

    @Override
    public Boolean execute(UserService userService) {
        return userService.deleteUser(authorizationHeader, reuqest);
    }
}
