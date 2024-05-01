package com.example.toDoList.UserAccount.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.UserAccount.UserService;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.reuqest.UpdateUserDataReuqest;

public class UpdateUserCommand implements Command<UserUpdateResponse, UserService> {

    private UpdateUserDataReuqest reuqest;
    private String authorizationHeader;

    private UpdateUserCommand(UpdateUserDataReuqest reuqest, String authorizationHeader) {
        this.reuqest = reuqest;
        this.authorizationHeader = authorizationHeader;
    }

    public static UpdateUserCommand from(UpdateUserDataReuqest reuqest, String authorizationHeader){
        return new UpdateUserCommand(reuqest,authorizationHeader);
    }

    @Override
    public UserUpdateResponse execute(UserService userService) {
        return userService.updateUserData(reuqest,authorizationHeader);
    }
}
