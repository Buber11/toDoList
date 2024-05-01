package com.example.toDoList.payload.response;

import lombok.Builder;

@Builder
public record UserUpdateResponse(
        String name,
        String surname,
        String email,
        String token,
        Long expiresIn
) {
}
