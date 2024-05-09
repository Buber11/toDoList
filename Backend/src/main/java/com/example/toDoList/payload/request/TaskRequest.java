package com.example.toDoList.payload.request;

public record TaskRequest(
        String titleTask,

        Long listId,

        Boolean completed
) {
}
