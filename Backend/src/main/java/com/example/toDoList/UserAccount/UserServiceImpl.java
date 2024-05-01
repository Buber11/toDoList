package com.example.toDoList.UserAccount;

import com.example.toDoList.Models.User.User;
import com.example.toDoList.Models.User.UserRepository;
import com.example.toDoList.Security.JwtService;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.reuqest.DeleteUserReuqest;
import com.example.toDoList.payload.reuqest.UpdateUserDataReuqest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService{

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            JwtService jwtService,
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {

        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean deleteUser(String authorizationHeader, DeleteUserReuqest reuqest) {
        String token = jwtService.extractJwtToken(authorizationHeader);
        String email = jwtService.extractUsernameFromToken(token);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            reuqest.password()
                    )
            );
        } catch (AuthenticationException e) {
            System.out.println("error authentication " + e.getMessage());
            return false;
        }

        Long userId = jwtService.extractIdFromToken(token);

        userRepository.deleteById(userId);
        return true;

    }

    @Override
    public UserUpdateResponse updateUserData(UpdateUserDataReuqest reuqest, String authorizationHeader) {
        String token = jwtService.extractJwtToken(authorizationHeader);
        Long userId = jwtService.extractIdFromToken(token);
        if(userRepository.existsById(userId)){
            User updatedUser = User.builder()
                    .userId(userId)
                    .name(reuqest.name())
                    .surname(reuqest.surname())
                    .email(reuqest.email())
                    .enabled(true)
                    .password(passwordEncoder.encode(reuqest.password()))
                    .build();
            userRepository.save(updatedUser);

            HashMap claims = new HashMap();
            claims.put("userId",userId);

            UserUpdateResponse response = UserUpdateResponse.builder()
                    .email(updatedUser.getEmail())
                    .name(updatedUser.getName())
                    .surname(updatedUser.getSurname())
                    .expiresIn(jwtService.getExpirationTime())
                    .token(jwtService.generateToken(claims,updatedUser))
                    .build();
            
            return response;

        }else {
            return null;
        }
    }
}
