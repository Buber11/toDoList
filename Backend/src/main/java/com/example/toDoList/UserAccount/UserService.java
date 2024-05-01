package com.example.toDoList.UserAccount;

import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.reuqest.DeleteUserReuqest;
import com.example.toDoList.payload.reuqest.UpdateUserDataReuqest;

public interface UserService {

    Boolean deleteUser(String authorizationHeader, DeleteUserReuqest reuqest);
    UserUpdateResponse updateUserData(UpdateUserDataReuqest reuqest, String authorizationHeader);

}
