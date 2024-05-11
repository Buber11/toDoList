package com.example.toDoList.User.UserBusinessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.User.UserBusinessLogic.UserService;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.request.UpdateUserDataRequest;
import jakarta.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements Command<UserUpdateResponse, UserService> {

    private final UpdateUserDataRequest reuqest;
    private final HttpServletRequest httpServletRequest;

    public UpdateUserCommand(UpdateUserDataRequest reuqest, HttpServletRequest httpServletRequest) {
        this.reuqest = reuqest;
        this.httpServletRequest = httpServletRequest;
    }

    public static UpdateUserCommand from(UpdateUserDataRequest reuqest, HttpServletRequest httpServletRequest){
        return new UpdateUserCommand(reuqest,httpServletRequest);
    }

    @Override
    public UserUpdateResponse execute(UserService userService) {
        return userService.updateUserData(reuqest, httpServletRequest);
    }
}
