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
    public ResponseEntity addNewTask(HttpServletRequest requestHttp, @RequestBody TaskRequest taskRequest){
        TaskResponse response = fasada.handle(AddTaskCommand.from(requestHttp, taskRequest));
        if(response != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteTask(@RequestParam("taskId") long taskId, HttpServletRequest requestHttp){
        boolean response = fasada.handle(DeleteTaskCommand.from(requestHttp,taskId));

        if(response){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }

    }


}
