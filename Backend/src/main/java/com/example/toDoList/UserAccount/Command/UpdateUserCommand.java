package com.example.toDoList.UserAccount.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.UserAccount.UserService;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.reuqest.UpdateUserDataReuqest;

public class UpdateUserCommand implements Command<UserUpdateResponse, UserService> {

    private UpdateUserDataReuqest reuqest;
    private Long userId;

    private UpdateUserCommand(UpdateUserDataReuqest reuqest, Long userId) {
        this.reuqest = reuqest;
        this.userId = userId;
    }

    public static UpdateUserCommand from(UpdateUserDataReuqest reuqest, Long userId){
        return new UpdateUserCommand(reuqest,userId);
    }

    @Override
    public UserUpdateResponse execute(UserService userService) {
        return userService.updateUserData(reuqest, userId);
    }
}
