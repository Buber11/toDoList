package com.example.toDoList.UserAccount;

import com.example.toDoList.Models.User.UserRepository;
import com.example.toDoList.Security.JwtService;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.payload.reuqest.DeleteUserReuqest;
import com.example.toDoList.payload.reuqest.UpdateUserDataReuqest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(JwtService jwtService, UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
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
    public UserInfoResponse updateUserData(UpdateUserDataReuqest reuqest) {
        return null;
    }
}
