package com.example.toDoList.payload.request;

public record UpdateUserDataRequest(
        String password,
        String name,
        String surname,
        String email
){ }
