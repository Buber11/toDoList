package com.example.toDoList.payload.request;

public record LoginRequest(
        String email,
        String password
) {}