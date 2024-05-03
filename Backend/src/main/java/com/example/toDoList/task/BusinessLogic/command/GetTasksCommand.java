package com.example.toDoList.task.BusinessLogic.command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.payload.response.TaskResponse;

import com.example.toDoList.task.BusinessLogic.TaskService;

import java.util.List;


public class GetTasksCommand implements Command< List<TaskResponse>, TaskService> {

    private Long userId;

    private GetTasksCommand(Long userId) {
        this.userId = userId;
    }
    public static GetTasksCommand from(long userId){
        return new GetTasksCommand(userId);
    }

    @Override
    public List<TaskResponse> execute(TaskService taskService) {
        return taskService.getAllTasksForUser(userId);
    }
}
