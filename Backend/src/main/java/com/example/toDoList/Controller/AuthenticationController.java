package com.example.toDoList.Controller;

import com.example.toDoList.DTO.UserDTO.LoginResponse;
import com.example.toDoList.DTO.LoginUserDto;
import com.example.toDoList.DTO.UserDTO.SignUPAnswerDto;
import com.example.toDoList.DTO.UserDTO.SignUpDTO;
import com.example.toDoList.Entities.User;
import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.Fasada.commands.CreateNewUserCommand;
import com.example.toDoList.Service.JwtService;
import com.example.toDoList.Service.AuthenticationService;
import com.example.toDoList.Service.AuthenticationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final Fasada fasada;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService,
                                    Fasada fasada,
                                    AuthenticationServiceImpl authenticationService) {
        this.jwtService = jwtService;
        this.fasada = fasada;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUPAnswerDto> register(@RequestBody SignUpDTO signUpDTO) {
        SignUPAnswerDto createdUserDto = fasada.handle(CreateNewUserCommand.from(signUpDTO));
        return ResponseEntity.ok(createdUserDto);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Optional<User> authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser.get());
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
