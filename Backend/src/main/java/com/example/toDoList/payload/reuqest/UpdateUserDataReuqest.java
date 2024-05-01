package com.example.toDoList.payload.reuqest;

public record UpdateUserDataReuqest(
        String password,
        String name,
        String surname,
        String email
){ }
