package com.example.toDoList.UserAccount;

import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.UserAccount.Command.UserDeleteCommand;
import com.example.toDoList.payload.reuqest.DeleteUserReuqest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


}
