package com.example.toDoList.payload.response;

import lombok.Builder;

@Builder
public record TaskResponse(
        long taskId,
        String titleTask,
        Boolean complited

) { }
