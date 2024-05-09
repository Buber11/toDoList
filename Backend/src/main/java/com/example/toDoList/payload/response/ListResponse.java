package com.example.toDoList.payload.response;


import com.example.toDoList.List.TaskList;

import java.util.List;

public record ListResponse(
        List<TaskList> taskLists
) {
}
