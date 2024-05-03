package com.example.toDoList.payload.response;

import lombok.Builder;

@Builder
public record TaskResponse(

        String taskId,
        String titleTask,
        Boolean complited

) { }
