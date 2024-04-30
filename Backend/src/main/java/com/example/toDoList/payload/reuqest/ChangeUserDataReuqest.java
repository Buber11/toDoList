package com.example.toDoList.payload.reuqest;

public record ChangeUserDataReuqest(
        String password,
        String name,
        String surname,
        String email
){ }
