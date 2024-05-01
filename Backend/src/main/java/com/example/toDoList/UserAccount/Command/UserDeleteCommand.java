package com.example.toDoList.UserAccount.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.UserAccount.UserService;
import com.example.toDoList.payload.reuqest.DeleteUserReuqest;


public class UserDeleteCommand implements Command<Boolean, UserService> {

    private String authorizationHeader;
    private DeleteUserReuqest reuqest;

    private UserDeleteCommand(String authorizationHeader, DeleteUserReuqest reuqest){
        this.authorizationHeader = authorizationHeader;
        this.reuqest = reuqest;
    }
    public static UserDeleteCommand from(String authorizationHeader, DeleteUserReuqest reuqest){
        return new UserDeleteCommand(authorizationHeader, reuqest);
    }

    @Override
    public Boolean execute(UserService userService) {
        return userService.deleteUser(authorizationHeader, reuqest);
    }
}
