package com.example.toDoList.Auth;

import com.example.toDoList.Auth.commands.LoginUserCommand;
import com.example.toDoList.Auth.commands.LogoutUserCommand;
import com.example.toDoList.Auth.commands.RefreshTokenCommand;
import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.payload.reuqest.SignUpReuqest;
import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.reuqest.LoginRequest;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.Auth.commands.SignUpUserCommand;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {


    private final Fasada fasada;

    public AuthenticationController(Fasada fasada,
                                    AuthenticationServiceImpl authenticationService) {
        this.fasada = fasada;
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

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){

        boolean response = fasada.handle(LogoutUserCommand.from(authorizationHeader));
        if (response) {
            return ResponseEntity.ok("Logout successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtTokenInfoResponse> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        JwtTokenInfoResponse jwtTokenInfoResponse = fasada.handle(RefreshTokenCommand.from(authorizationHeader));
        if(jwtTokenInfoResponse != null){
            return ResponseEntity.ok(jwtTokenInfoResponse);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @GetMapping("/test")
    public String test(){
        return "nie działa kurwa";
    }

}
