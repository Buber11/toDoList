package com.example.toDoList.task.BusinessLogic.command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.task.BusinessLogic.TaskService;

public class DeleteTaskCommand implements Command<Boolean, TaskService> {

    private long userId;
    private String taskId;

    private DeleteTaskCommand(long userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }
    public static DeleteTaskCommand from(long userId, String taskId){
        return new DeleteTaskCommand(userId, taskId);
    }

    @Override
    public Boolean execute(TaskService taskService) {
        return taskService.deleteTask(userId,taskId);
    }
}
