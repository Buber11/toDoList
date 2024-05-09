package com.example.toDoList.List.businessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.List.businessLogic.ListService;
import com.example.toDoList.payload.response.ListResponse;
import jakarta.servlet.http.HttpServletRequest;

public class GetListCommand implements Command<ListResponse, ListService> {

    private HttpServletRequest request;

    private GetListCommand(HttpServletRequest request) {
        this.request = request;
    }
    public static GetListCommand from(HttpServletRequest request){
        return new GetListCommand(request);
    }

    @Override
    public ListResponse execute(ListService listService) {
        return listService.getLists(request);
    }
}
