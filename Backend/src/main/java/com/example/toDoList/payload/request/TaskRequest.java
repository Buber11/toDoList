package com.example.toDoList.payload.request;

public record TaskRequest(
        String taskTitle,

        long listId,

        Boolean completed
) {
}
