package com.example.toDoList.task.BusinessLogic.command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.task.BusinessLogic.TaskService;

public class CompleteTaskCommand implements Command<Boolean, TaskService> {

    private final long taskId;

    private CompleteTaskCommand(long taskId) {
        this.taskId = taskId;
    }
    public static CompleteTaskCommand from(long taskId){
        return new CompleteTaskCommand(taskId);
    }

    @Override
    public Boolean execute(TaskService taskService) {
        return taskService.completeTask(taskId);
    }
}
