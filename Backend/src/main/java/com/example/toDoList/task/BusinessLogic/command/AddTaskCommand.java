package com.example.toDoList.task.BusinessLogic.command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.request.TaskRequest;
import com.example.toDoList.task.BusinessLogic.TaskService;
import jakarta.servlet.http.HttpServletRequest;

public class AddTaskCommand implements Command<TaskResponse, TaskService> {

    private final HttpServletRequest requestHttp;
    private final TaskRequest reuqest;

    private AddTaskCommand(HttpServletRequest requestHttp, TaskRequest reuqest) {
        this.requestHttp = requestHttp;
        this.reuqest = reuqest;
    }

    public static AddTaskCommand from(HttpServletRequest requestHttp, TaskRequest reuqest){
        return new AddTaskCommand(requestHttp,reuqest);
    }
    @Override
    public TaskResponse execute(TaskService taskService) {
        return taskService.addNewTask(requestHttp,reuqest);
    }
}
