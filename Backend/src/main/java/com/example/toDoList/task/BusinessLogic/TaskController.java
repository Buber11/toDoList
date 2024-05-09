package com.example.toDoList.task.BusinessLogic;

import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.payload.response.TaskResponse;
import com.example.toDoList.payload.request.TaskRequest;
import com.example.toDoList.task.BusinessLogic.command.AddTaskCommand;
import com.example.toDoList.task.BusinessLogic.command.DeleteTaskCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/task")
@RestController
public class TaskController {
    private final Fasada fasada;

    public TaskController(Fasada fasada) {
        this.fasada = fasada;
    }

    @PostMapping("/add")
    public ResponseEntity addNewTask(HttpServletRequest request, @RequestBody TaskRequest taskRequest){
        System.out.println("we");
        Long userId = (Long) request.getAttribute("id");
        TaskResponse response = fasada.handle(AddTaskCommand.from(userId, taskRequest));
        if(response != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteTask(@RequestParam String taskId, HttpServletRequest request){
        Long userId = (Long) request.getAttribute("id");
        boolean response = fasada.handle(DeleteTaskCommand.from(userId,taskId));
        if(response){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}