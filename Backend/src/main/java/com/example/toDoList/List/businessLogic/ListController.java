package com.example.toDoList.List.businessLogic;

import com.example.toDoList.Fasada.Fasada;
import com.example.toDoList.List.businessLogic.Command.AddListCommand;
import com.example.toDoList.List.businessLogic.Command.DeleteListCommand;
import com.example.toDoList.List.businessLogic.Command.GetListCommand;
import com.example.toDoList.List.businessLogic.Command.UpDateTitleListCommand;
import com.example.toDoList.payload.request.AddListRequest;
import com.example.toDoList.payload.request.ChangeTitleListRequest;
import com.example.toDoList.payload.request.DeleteListRequest;
import com.example.toDoList.payload.response.ListIdResponce;
import com.example.toDoList.payload.response.ListResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{listId}")
    public ResponseEntity deleteList(@PathVariable long listId, HttpServletRequest httpRequest){
        boolean listDeleted = fasada.handle(DeleteListCommand.from(listId,httpRequest));
        if(listDeleted){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity addList(@RequestBody AddListRequest request, HttpServletRequest requestHttp){
        ListIdResponce responce = fasada.handle(AddListCommand.from(requestHttp,request));
        return ResponseEntity.ok(responce);
    }

    @PutMapping("/change-title")
    public ResponseEntity changeTitle(@RequestBody ChangeTitleListRequest request, HttpServletRequest requestHttp){
        boolean changedTitleList = fasada.handle(UpDateTitleListCommand.from(requestHttp,request));

        if(changedTitleList){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}
