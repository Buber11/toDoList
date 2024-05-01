package com.example.toDoList.UserAccount;

import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.UserAccount.Command.GetUserCommand;
import com.example.toDoList.UserAccount.Command.UpdateUserCommand;
import com.example.toDoList.UserAccount.Command.UserDeleteCommand;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.reuqest.DeleteUserReuqest;
import com.example.toDoList.payload.reuqest.UpdateUserDataReuqest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user/management")
@RestController()
public class UserManagementController {

    private final Fasada fasada;

    public UserManagementController(Fasada fasada) {
        this.fasada = fasada;
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody DeleteUserReuqest reuqest
    ){

        Boolean response = fasada.handle(UserDeleteCommand.from(authorizationHeader,reuqest));

        if(response){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(
            @RequestBody UpdateUserDataReuqest reuqest,
            HttpServletRequest request
    ){
        Long userId = (Long) request.getAttribute("id");
        UserUpdateResponse response = fasada.handle(UpdateUserCommand.from(reuqest,userId));

        if(response != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/getInfo")
    public ResponseEntity getInfoAboutUser(HttpServletRequest request){
        Long userId = (Long) request.getAttribute("id");
        UserInfoResponse response = fasada.handle(GetUserCommand.from(userId));
        if(response != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
