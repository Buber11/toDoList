package com.example.toDoList.Models.Token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRespository extends JpaRepository<Token,Long> {

}
