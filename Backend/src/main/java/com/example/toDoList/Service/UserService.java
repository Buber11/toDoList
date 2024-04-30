package com.example.toDoList.Service;

import com.example.toDoList.Entities.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);
    List<User> getAllUsers();
}
