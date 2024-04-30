package com.example.toDoList.Service;

import com.example.toDoList.Entities.User;
import com.example.toDoList.JPARespository.JPAUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final JPAUserRepository userRepository;



    public UserServiceImpl(JPAUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){

        userRepository.save(user);

    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }



}
