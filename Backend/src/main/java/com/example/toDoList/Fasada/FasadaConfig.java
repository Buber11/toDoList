package com.example.toDoList.Fasada;

import com.example.toDoList.Service.AuthenticationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FasadaConfig {

    @Bean
    public Fasada setFasada(AuthenticationServiceImpl authenticationService){
        return new Fasada(authenticationService);
    }
}
