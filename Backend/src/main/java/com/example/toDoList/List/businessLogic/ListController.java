package com.example.toDoList.List.businessLogic;

import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.List.businessLogic.Command.GetListCommand;
import com.example.toDoList.payload.request.ListRequest;
import com.example.toDoList.payload.response.ListResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/list")
public class ListController {

    private Fasada fasada;

    public ListController(Fasada fasada) {
        this.fasada = fasada;
    }

    @GetMapping("/get")
    public ResponseEntity getLists(HttpServletRequest request){
        ListResponse response = fasada.handle(GetListCommand.from(request));
        return ResponseEntity.ok(response);
    }

}
