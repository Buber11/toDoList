package com.example.toDoList.Auth;

import com.example.toDoList.Security.JwtService;
import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.reuqest.SignUpReuqest;
import com.example.toDoList.payload.reuqest.LoginRequest;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.Models.User.User;
import com.example.toDoList.Models.User.JPAUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JPAUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationServiceImpl(
            JPAUserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public JwtTokenInfoResponse authenticate(LoginRequest input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.email(),
                            input.password()
                    )
            );
        } catch (AuthenticationException e) {
            System.out.println("error authentication " + e.getMessage());
            return null;
        }

        Optional<User> authenticatedUser = userRepository.findByEmail(input.email());
        if (userRepository.existsByEmail(input.email())){
            String jwtToken = jwtService.generateToken(authenticatedUser.get());
            JwtTokenInfoResponse jwtTokenInfoResponse = new JwtTokenInfoResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
            return jwtTokenInfoResponse;
        }else{
            return null;
        }
    }
    public UserInfoResponse signup(SignUpReuqest signUpDTO) {
        System.out.println(signUpDTO);

        UserInfoResponse addedUserDto;
        User newUser;

        if(! userRepository.existsByEmail(signUpDTO.email())) {

            newUser = User.builder()
                    .email(signUpDTO.email())
                    .name(signUpDTO.name())
                    .surname(signUpDTO.surname())
                    .password(passwordEncoder.encode(signUpDTO.password()))
                    .build();

            userRepository.save(newUser);

            addedUserDto = UserInfoResponse.builder()
                    .email(newUser.getEmail())
                    .name(newUser.getName())
                    .surname(newUser.getSurname())
                    .build();

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
