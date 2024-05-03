package com.example.toDoList.task.BusinessLogic;

import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.request.TaskRequest;

import java.util.List;

public interface TaskService {

    public List<TaskResponse> getAllTasksForUser(Long userId);
    public TaskResponse addNewTask(Long userId, TaskRequest reuqest);

}
