package com.example.toDoList.task.BusinessLogic.command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.task.BusinessLogic.TaskService;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteTaskCommand implements Command<Boolean, TaskService> {

    private HttpServletRequest requestHttp;
    private long taskId;

    public DeleteTaskCommand(HttpServletRequest requestHttp, long taskId) {
        this.requestHttp = requestHttp;
        this.taskId = taskId;
    }

    public static DeleteTaskCommand from(HttpServletRequest requestHttp, long taskId){
        return new DeleteTaskCommand(requestHttp, taskId);
    }

    @Override
    public Boolean execute(TaskService taskService) {
        return taskService.deleteTask(taskId, requestHttp);
    }
}
