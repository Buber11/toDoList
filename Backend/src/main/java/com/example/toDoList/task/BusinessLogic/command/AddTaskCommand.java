package com.example.toDoList.task.BusinessLogic.command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.request.TaskRequest;
import com.example.toDoList.task.BusinessLogic.TaskService;

public class AddTaskCommand implements Command<TaskResponse, TaskService> {

    private final Long userId;
    private final TaskRequest reuqest;

    private AddTaskCommand(Long userId, TaskRequest reuqest) {
        this.userId = userId;
        this.reuqest = reuqest;
    }
    public static AddTaskCommand from(Long userId, TaskRequest reuqest){
        return new AddTaskCommand(userId,reuqest);
    }
    @Override
    public TaskResponse execute(TaskService taskService) {
        return taskService.addNewTask(userId,reuqest);
    }
}
