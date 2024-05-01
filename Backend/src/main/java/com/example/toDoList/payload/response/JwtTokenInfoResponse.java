package com.example.toDoList.payload.response;

import lombok.Builder;

@Builder
public record JwtTokenInfoResponse(
    String token,
    Long expiresIn

){}


