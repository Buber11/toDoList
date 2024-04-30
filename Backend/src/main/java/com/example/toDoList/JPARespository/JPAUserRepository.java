package com.example.toDoList.JPARespository;

import com.example.toDoList.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPAUserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);

}
