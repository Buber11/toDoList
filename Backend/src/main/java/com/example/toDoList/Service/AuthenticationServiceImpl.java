package com.example.toDoList.Service;

import com.example.toDoList.DTO.LoginUserDto;
import com.example.toDoList.DTO.Mapper.UserMapper;
import com.example.toDoList.DTO.UserDTO.SignUPAnswerDto;
import com.example.toDoList.DTO.UserDTO.SignUpDTO;
import com.example.toDoList.Entities.User;
import com.example.toDoList.JPARespository.JPAUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JPAUserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(
            JPAUserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> authenticate(LoginUserDto input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            System.out.println("error authentication " + e.getMessage());
            return null;
        }
        return userRepository.findByEmail(input.getEmail());
    }
    public SignUPAnswerDto signup(SignUpDTO signUpDTO) {
        SignUPAnswerDto addedUserDto;
        User newUser = userMapper.ToUser(signUpDTO);

        if(userRepository.findByEmail(signUpDTO.email()).isEmpty()) {
            
            newUser.setPassword(passwordEncoder.encode(signUpDTO.password()));
            userRepository.save(newUser);
            addedUserDto = userMapper.toUserDto(newUser);

            if( ! (addedUserDto.name() == signUpDTO.name()
                    && addedUserDto.email() == signUpDTO.email()
                    && addedUserDto.surname() == signUpDTO.surname() ) ){
                addedUserDto =null;
            }

        }else {
            addedUserDto = null;
        }

        return addedUserDto;
    }
}
