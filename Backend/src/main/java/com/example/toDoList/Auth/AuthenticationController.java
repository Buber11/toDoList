package com.example.toDoList.Auth;

import com.example.toDoList.Auth.commands.LoginUserCommand;
import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.payload.reuqest.SignUpReuqest;
import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.reuqest.LoginRequest;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.Auth.commands.SignUpUserCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {


    private final Fasada fasada;
    private final AuthenticationService authenticationService;

    public AuthenticationController(Fasada fasada,
                                    AuthenticationServiceImpl authenticationService) {
        this.fasada = fasada;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserInfoResponse> register(@RequestBody SignUpReuqest signUpDTO) {

        UserInfoResponse createdUserDto = fasada.handle(SignUpUserCommand.from(signUpDTO));

        if(createdUserDto != null) {
            return ResponseEntity.ok(createdUserDto);
        }{
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<JwtTokenInfoResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        JwtTokenInfoResponse jwtTokenInfoResponse = fasada.handle(LoginUserCommand.from(loginRequest));

        if(jwtTokenInfoResponse == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(jwtTokenInfoResponse);
        }
    }
}
