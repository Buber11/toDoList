package com.example.toDoList.payload.request;

public record TaskRequest(
        String taskTitle,

        Long listId,

        Boolean completed
) {
}
