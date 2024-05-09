package com.example.toDoList.task.BusinessLogic;

import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.request.TaskRequest;

import javax.swing.text.StyledEditorKit;
import java.util.List;

public interface TaskService {

    public TaskResponse addNewTask(Long userId, TaskRequest reuqest);
    public Boolean deleteTask(Long userId, String taskIdEncrypted);

}
