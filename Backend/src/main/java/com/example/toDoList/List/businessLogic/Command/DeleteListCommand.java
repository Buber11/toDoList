package com.example.toDoList.List.businessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.List.businessLogic.ListService;
import com.example.toDoList.payload.request.DeleteListRequest;
import jakarta.servlet.http.HttpServletRequest;


public class DeleteListCommand implements Command<Boolean, ListService> {

    private DeleteListRequest request;
    private HttpServletRequest httpRequest;

    private DeleteListCommand(DeleteListRequest request, HttpServletRequest httpRequest) {
        this.request = request;
        this.httpRequest = httpRequest;
    }

    public static DeleteListCommand from(DeleteListRequest request, HttpServletRequest httpRequest){
        return new DeleteListCommand(request,httpRequest);
    }

    @Override
    public Boolean execute(ListService listService) {
        return listService.deleteList(request,httpRequest);
    }
}
