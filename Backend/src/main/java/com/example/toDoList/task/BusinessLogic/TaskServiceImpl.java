package com.example.toDoList.task.BusinessLogic;

import com.example.toDoList.Security.AESCipher;
import com.example.toDoList.User.UserRepository;
import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.request.TaskRequest;
import com.example.toDoList.task.Task;
import com.example.toDoList.task.TaskRepository;
import com.sun.net.httpserver.HttpsServer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private AESCipher aesCipher;

    public TaskServiceImpl(UserRepository userRepository,
                           TaskRepository taskRepository,
                           AESCipher aesCipher
    ) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.aesCipher = aesCipher;
    }

    @Override
    public TaskResponse addNewTask(HttpServletRequest requestHttp, TaskRequest reuqest) {
        long userId = (long) requestHttp.getAttribute("id");
        Task newTask = Task.builder()
                    .listId(reuqest.listId())
                    .taskTitle(reuqest.taskTitle())
                    .userId(userId)
                    .completed(reuqest.completed())
                    .build();
        taskRepository.save(newTask);

        try {
            return TaskResponse.builder()
                    .taskId(newTask.getTaskId())
                    .titleTask(newTask.getTaskTitle())
                    .complited(newTask.getCompleted())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public Boolean deleteTask(long taskId, HttpServletRequest request) {
        long userId = (long) request.getAttribute("id");
        if(taskRepository.existsByTaskIdAndUserId(taskId,userId)) {
            taskRepository.deleteById(taskId);
            return true;
        }else {
            return false;
        }
    }
}
