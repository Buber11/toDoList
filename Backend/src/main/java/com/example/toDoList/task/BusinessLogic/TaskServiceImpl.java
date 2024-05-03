package com.example.toDoList.task.BusinessLogic;

import com.example.toDoList.User.User;
import com.example.toDoList.User.UserRepository;
import com.example.toDoList.payload.response.TaskResponse;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    private UserRepository userRepository;

    public TaskServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskResponse> getAllTasksForUser(Long userId) {

        Optional<User> userOpt = userRepository.findById(userId);
        if(! userOpt.isEmpty()){
            var user = userOpt.get();
            var tasks = user.getTasks();
            var response = tasks.stream()
                    .map( task -> {
                        return TaskResponse.builder()
                                .titleTask(task.getTitleTask())
                                .complited(task.getCompleted())
                                .build();
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
}
