package com.example.toDoList.User.UserBusinessLogic;

import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.request.DeleteUserRequest;
import com.example.toDoList.payload.request.UpdateUserDataRequest;

public interface UserService {

    Boolean deleteUser(String authorizationHeader, DeleteUserRequest reuqest);
    UserUpdateResponse updateUserData(UpdateUserDataRequest reuqest, Long userId);

    UserInfoResponse getInfoAboutUser(Long userId);


}
