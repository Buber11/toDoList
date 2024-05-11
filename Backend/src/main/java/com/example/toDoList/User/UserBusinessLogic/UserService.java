package com.example.toDoList.User.UserBusinessLogic;

import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.request.DeleteUserRequest;
import com.example.toDoList.payload.request.UpdateUserDataRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    void deleteUser(HttpServletRequest httpServletRequest);
    UserUpdateResponse updateUserData(UpdateUserDataRequest reuqest, HttpServletRequest httpServletRequest);

    UserInfoResponse getInfoAboutUser(HttpServletRequest httpServletRequest);


}
