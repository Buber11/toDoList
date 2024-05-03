package com.example.toDoList.payload.response;

import lombok.Builder;

@Builder
public record TaskResponse(
        String titleTask,
        Boolean complited

) { }
