package com.example.toDoList.payload.reuqest;

public record LoginRequest(
        String email,
        String password
) {}