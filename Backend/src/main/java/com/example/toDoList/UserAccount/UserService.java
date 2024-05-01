package com.example.toDoList.UserAccount;

import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.payload.reuqest.DeleteUserReuqest;
import com.example.toDoList.payload.reuqest.UpdateUserDataReuqest;

public interface UserService {

    Boolean deleteUser(String authorizationHeader, DeleteUserReuqest reuqest);
    UserInfoResponse updateUserData(UpdateUserDataReuqest reuqest);

}
