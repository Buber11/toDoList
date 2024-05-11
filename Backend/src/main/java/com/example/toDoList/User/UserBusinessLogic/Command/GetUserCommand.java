package com.example.toDoList.User.UserBusinessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.User.UserBusinessLogic.UserService;
import com.example.toDoList.payload.response.UserInfoResponse;
import jakarta.servlet.http.HttpServletRequest;

public class GetUserCommand implements Command<UserInfoResponse, UserService> {

    private final HttpServletRequest httpServletRequest

    private GetUserCommand(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public static GetUserCommand from(HttpServletRequest httpServletRequest){
        return new GetUserCommand(httpServletRequest);
    }
    @Override
    public UserInfoResponse execute(UserService userService) {
        return userService.getInfoAboutUser(httpServletRequest);
    }
}
