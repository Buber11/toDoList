package com.example.toDoList.List.businessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.List.businessLogic.ListService;
import com.example.toDoList.payload.request.DeleteListRequest;
import jakarta.servlet.http.HttpServletRequest;


public class DeleteListCommand implements Command<Boolean, ListService> {

    private Long listId;
    private HttpServletRequest httpRequest;

    private DeleteListCommand( Long listId, HttpServletRequest httpRequest) {
        this.listId = listId;
        this.httpRequest = httpRequest;
    }

    public static DeleteListCommand from( Long listId, HttpServletRequest httpRequest){
        return new DeleteListCommand(listId,httpRequest);
    }

    @Override
    public Boolean execute(ListService listService) {
        return listService.deleteList(listId,httpRequest);
    }
}
