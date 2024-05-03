package com.example.toDoList.task.BusinessLogic;

import com.example.toDoList.Security.AESCipher;
import com.example.toDoList.User.User;
import com.example.toDoList.User.UserRepository;
import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.request.TaskRequest;
import com.example.toDoList.task.Task;
import com.example.toDoList.task.TaskRepository;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<TaskResponse> getAllTasksForUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(! userOpt.isEmpty()){
            var user = userOpt.get();
            var tasks = user.getTasks();
            var response = tasks.stream()
                    .map( task -> {
                        try {
                            return TaskResponse.builder()
                                    .taskId(aesCipher.encrypt(task.getTaskId().toString()))
                                    .titleTask(task.getTitleTask())
                                    .complited(task.getCompleted())
                                    .build();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toCollection(LinkedList::new));

            if(! response.isEmpty()){
                return response;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public TaskResponse addNewTask(Long userId, TaskRequest reuqest) {
        if(userRepository.existsById(userId) ){
            Task newTask = Task.builder()
                    .userId(userId)
                    .titleTask(reuqest.titleTask())
                    .completed(reuqest.completed())
                    .build();
            taskRepository.save(newTask);

            try {
                return TaskResponse.builder()
                        .taskId(aesCipher.encrypt(newTask.getTaskId().toString()))
                        .titleTask(newTask.getTitleTask())
                        .complited(newTask.getCompleted())
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            return null;
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
