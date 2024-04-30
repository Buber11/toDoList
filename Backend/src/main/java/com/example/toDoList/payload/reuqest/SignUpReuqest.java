package com.example.toDoList.payload.reuqest;

public record SignUpReuqest(
        String password,
        String name,
        String surname,
        String email
) {}