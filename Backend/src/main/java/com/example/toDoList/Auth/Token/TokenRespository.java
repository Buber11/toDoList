package com.example.toDoList.Auth.Token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRespository extends JpaRepository<Token,Long> {

    Boolean existsByToken(String token);

}
