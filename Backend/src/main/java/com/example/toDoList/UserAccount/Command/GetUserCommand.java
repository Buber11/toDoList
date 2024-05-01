package com.example.toDoList.UserAccount.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.UserAccount.UserService;
import com.example.toDoList.payload.response.UserInfoResponse;

public class GetUserCommand implements Command<UserInfoResponse, UserService> {

    private Long userId;

    private GetUserCommand(Long userId) {
        this.userId = userId;
    }

    public static GetUserCommand from(Long userId){
        return new GetUserCommand(userId);
    }
    @Override
    public UserInfoResponse execute(UserService userService) {
        return userService.getInfoAboutUser(userId);
    }
}
