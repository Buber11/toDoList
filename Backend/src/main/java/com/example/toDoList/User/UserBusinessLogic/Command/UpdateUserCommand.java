package com.example.toDoList.User.UserBusinessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.User.UserBusinessLogic.UserService;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.request.UpdateUserDataRequest;

public class UpdateUserCommand implements Command<UserUpdateResponse, UserService> {

    private UpdateUserDataRequest reuqest;
    private Long userId;

    private UpdateUserCommand(UpdateUserDataRequest reuqest, Long userId) {
        this.reuqest = reuqest;
        this.userId = userId;
    }

    public static UpdateUserCommand from(UpdateUserDataRequest reuqest, Long userId){
        return new UpdateUserCommand(reuqest,userId);
    }

    @Override
    public UserUpdateResponse execute(UserService userService) {
        return userService.updateUserData(reuqest, userId);
    }
}
