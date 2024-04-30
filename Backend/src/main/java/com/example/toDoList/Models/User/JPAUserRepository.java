package com.example.toDoList.Models.User;

import com.example.toDoList.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface JPAUserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);

   Optional<User> findByEmail(String email);

}
