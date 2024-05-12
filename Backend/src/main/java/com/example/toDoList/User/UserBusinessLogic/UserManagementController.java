package com.example.toDoList.User.UserBusinessLogic;

import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.User.UserBusinessLogic.Command.GetUserCommand;
import com.example.toDoList.User.UserBusinessLogic.Command.UpdateUserCommand;
import com.example.toDoList.User.UserBusinessLogic.Command.DeleteUserCommand;
import com.example.toDoList.payload.response.UserInfoResponse;
import com.example.toDoList.payload.response.UserUpdateResponse;
import com.example.toDoList.payload.request.DeleteUserRequest;
import com.example.toDoList.payload.request.UpdateUserDataRequest;
import jakarta.servlet.http.HttpServletRequest;
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
            HttpServletRequest httpServletRequest
    ){

        fasada.handle(DeleteUserCommand.from(httpServletRequest));

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

    @PutMapping("/update")
    public ResponseEntity updateUser(
            @RequestBody UpdateUserDataRequest reuqest,
            HttpServletRequest httpServletRequest
    ){
        UserUpdateResponse response = fasada.handle(UpdateUserCommand.from(reuqest,httpServletRequest));
        if(response != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/getInfo")
    public ResponseEntity getInfoAboutUser(HttpServletRequest httpServletRequest){
        UserInfoResponse response = fasada.handle(GetUserCommand.from(httpServletRequest));
        if(response != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
