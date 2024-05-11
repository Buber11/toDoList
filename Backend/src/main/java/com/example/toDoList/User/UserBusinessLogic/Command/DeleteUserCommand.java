package com.example.toDoList.User.UserBusinessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.User.UserBusinessLogic.UserService;
import com.example.toDoList.payload.request.DeleteUserRequest;
import jakarta.servlet.http.HttpServletRequest;


public class DeleteUserCommand implements Command<Void, UserService> {

    private final HttpServletRequest httpServletRequest;

    public DeleteUserCommand(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public static DeleteUserCommand from(HttpServletRequest httpServletRequest){
        return new DeleteUserCommand(httpServletRequest);
    }

    @Override
    public Void execute(UserService userService) {
        userService.deleteUser(httpServletRequest);
        return null;
    }
}
