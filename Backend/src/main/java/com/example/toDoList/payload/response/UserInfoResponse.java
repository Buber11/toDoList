package com.example.toDoList.payload.response;

import lombok.Builder;

@Builder
public record UserInfoResponse(
        String name,
        String surname,
        String email
) {


}
