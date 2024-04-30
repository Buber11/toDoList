package com.example.toDoList.DTO.UserDTO;

public record SignUpDTO(
        String password,
        String name,
        String surname,
        String email
) {}