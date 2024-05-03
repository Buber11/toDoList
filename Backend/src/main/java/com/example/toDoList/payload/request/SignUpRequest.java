package com.example.toDoList.payload.request;

public record SignUpRequest(
        String password,
        String name,
        String surname,
        String email
) {}