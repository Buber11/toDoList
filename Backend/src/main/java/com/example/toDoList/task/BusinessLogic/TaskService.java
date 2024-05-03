package com.example.toDoList.task.BusinessLogic;

import com.example.toDoList.payload.response.TaskResponse;

import java.util.List;

public interface TaskService {

    public List<TaskResponse> getAllTasksForUser(Long userId);

}
