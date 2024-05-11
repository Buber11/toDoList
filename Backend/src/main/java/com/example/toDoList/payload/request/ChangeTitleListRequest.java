package com.example.toDoList.payload.request;

public record ChangeTitleListRequest(
        String listTitle,
        long listId
) {
}
