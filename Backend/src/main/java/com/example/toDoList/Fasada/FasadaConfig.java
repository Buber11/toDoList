package com.example.toDoList.Fasada;

import com.example.toDoList.Auth.AuthenticationServiceImpl;
import com.example.toDoList.User.UserBusinessLogic.UserService;
import com.example.toDoList.task.BusinessLogic.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FasadaConfig {

    @Bean
    public Fasada setFasada(
            AuthenticationServiceImpl authenticationService,
            UserService userService,
            TaskService taskService
    ){
        return new Fasada(authenticationService, userService, taskService);
    }

}
