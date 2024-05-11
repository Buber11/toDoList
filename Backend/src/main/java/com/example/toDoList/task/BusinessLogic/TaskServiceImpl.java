package com.example.toDoList.task.BusinessLogic;

import com.example.toDoList.Security.AESCipher;
import com.example.toDoList.User.UserRepository;
import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.request.TaskRequest;
import com.example.toDoList.task.Task;
import com.example.toDoList.task.TaskRepository;
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
    public TaskResponse addNewTask(Long userId, TaskRequest reuqest) {
        Task newTask = Task.builder()
                    .listId(reuqest.listId())
                    .taskTitle(reuqest.taskTitle())
                    .userId(userId)
                    .completed(reuqest.completed())
                    .build();
        taskRepository.save(newTask);

        try {
            return TaskResponse.builder()
                    .taskId(aesCipher.encrypt(newTask.getTaskId().toString()))
                    .titleTask(newTask.getTaskTitle())
                    .complited(newTask.getCompleted())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public Boolean deleteTask(Long userId, String taskIdEncrypted) {
        Long taskId;

        try {
            taskId = Long.decode(aesCipher.decrypt(taskIdEncrypted));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Optional<Task> taskOpt = taskRepository.findById(taskId);

        if(!taskOpt.isEmpty()){
            Task task = taskOpt.get();

            //we use this in delete method to check if userId from token is the same like userId owner
            if(task.getUserId() == userId) {
                taskRepository.delete(task);
                return true;
            }
            else return false;

        }else {
            return false;
        }
    }
}
