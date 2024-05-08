package com.example.toDoList.Auth.AuthBusinessLogic;

import com.example.toDoList.Auth.AuthBusinessLogic.commands.LoginUserCommand;

import com.example.toDoList.Auth.AuthBusinessLogic.commands.LogoutUserCommand;
import com.example.toDoList.Auth.AuthBusinessLogic.commands.RefreshTokenCommand;
import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.payload.request.SignUpRequest;
import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.request.LoginRequest;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.Auth.AuthBusinessLogic.commands.SignUpUserCommand;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<UserInfoResponse> register(@RequestBody SignUpRequest signUpDTO) {

        UserInfoResponse createdUserDto = fasada.handle(SignUpUserCommand.from(signUpDTO));

        if(createdUserDto != null) {
            return ResponseEntity.ok(createdUserDto);
        }{
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        boolean userLogin = fasada.handle(LoginUserCommand.from(loginRequest,response));
        if(userLogin){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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
    public ResponseEntity refreshToken(@CookieValue("jwt_token") String token, HttpServletResponse response){
        boolean refreshedToken = fasada.handle(RefreshTokenCommand.from(token,response));

        if(refreshedToken){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(401).build();
        }
    }

}
