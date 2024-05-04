package com.example.toDoList.Auth;

import com.example.toDoList.Auth.Token.Token;
import com.example.toDoList.Auth.Token.TokenRespository;
import com.example.toDoList.User.User;
import com.example.toDoList.Security.JwtService;
import com.example.toDoList.payload.response.JwtTokenInfoResponse;
import com.example.toDoList.payload.request.SignUpRequest;
import com.example.toDoList.payload.request.LoginRequest;
import com.example.toDoList.payload.response.UserInfoResponse;

import com.example.toDoList.User.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRespository tokenRespository;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            TokenRespository tokenRespository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRespository = tokenRespository;
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

        HashMap extraClaims = new HashMap();
        extraClaims.put("userId", authenticatedUser.get().getUserId());

        String jwtToken = jwtService.generateToken(extraClaims,authenticatedUser.get());
        JwtTokenInfoResponse jwtTokenInfoResponse = JwtTokenInfoResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return jwtTokenInfoResponse;
    }
    public UserInfoResponse signup(SignUpRequest signUpDTO) {

        UserInfoResponse response;
        User newUser;

        if(! userRepository.existsByEmail(signUpDTO.email())) {

            newUser = User.builder()
                    .email(signUpDTO.email())
                    .name(signUpDTO.name())
                    .surname(signUpDTO.surname())
                    .password(passwordEncoder.encode(signUpDTO.password()))
                    .enabled(true)
                    .build();

            userRepository.save(newUser);

            response = UserInfoResponse.builder()
                    .email(newUser.getEmail())
                    .name(newUser.getName())
                    .surname(newUser.getSurname())
                    .build();

            if( ! (response.name() == signUpDTO.name()
                    && response.email() == signUpDTO.email()
                    && response.surname() == signUpDTO.surname() ) ){
                response =null;
            }

        }else {
            response = null;
        }

        return response;
    }

    @Override
    public boolean logout(String authorizationHeader) {

        String token = jwtService.extractJwtToken(authorizationHeader);
        String email = jwtService.extractUsernameFromToken(token);
        Date expirationDate = jwtService.extractClaim(token, Claims :: getExpiration);

        if( ! expirationDate.before(new Date())){
            Optional<User> userToLogout = userRepository.findByEmail(email);

            if(! userToLogout.isEmpty()){
                Token blackListToken = Token.builder()
                        .userId(userToLogout.get().getUserId())
                        .token(token)
                        .tokenExpirationDate(expirationDate)
                        .build();

                tokenRespository.save(blackListToken);
                SecurityContextHolder.clearContext();

                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public JwtTokenInfoResponse refreshToken(String authorizationHeader) {

        String token = jwtService.extractJwtToken(authorizationHeader);
        if(tokenRespository.existsByToken(token)){
            return null;
        } else {
            String email = jwtService.extractUsernameFromToken(token);
            Optional<User> authenticatedUser = userRepository.findByEmail(email);
            if (userRepository.existsByEmail(email)){
                System.out.println("weszło do jwt");
                String jwtToken = jwtService.generateToken(authenticatedUser.get());
                JwtTokenInfoResponse jwtTokenInfoResponse = JwtTokenInfoResponse.builder()
                        .token(jwtToken)
                        .expiresIn(jwtService.getExpirationTime())
                        .build();
                return jwtTokenInfoResponse;
            }else{
                return null;
            }
        }
    }
}
