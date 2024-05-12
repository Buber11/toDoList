package com.example.toDoList.task.BusinessLogic;

import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.request.TaskRequest;
import jakarta.servlet.http.HttpServletRequest;

import javax.swing.text.StyledEditorKit;
import java.util.List;

public interface TaskService {

    public TaskResponse addNewTask(HttpServletRequest requestHttp, TaskRequest reuqest);
    public Boolean deleteTask(long userId, HttpServletRequest requestHttp);
    public boolean completeTask(long taskId);

}
