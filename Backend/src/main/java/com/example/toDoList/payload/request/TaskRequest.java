package com.example.toDoList.payload.request;

public record TaskRequest(
        String titleTask,
        Boolean completed
) {
}
