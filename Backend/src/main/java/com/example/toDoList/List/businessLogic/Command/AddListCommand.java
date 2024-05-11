package com.example.toDoList.List.businessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.List.businessLogic.ListService;
import com.example.toDoList.payload.request.AddListRequest;
import com.example.toDoList.payload.response.ListIdResponce;
import jakarta.servlet.http.HttpServletRequest;

public class AddListCommand implements Command<ListIdResponce, ListService> {

    private final HttpServletRequest requestHttp;
    private final AddListRequest  request;

    private AddListCommand(HttpServletRequest requestHttp, AddListRequest request) {
        this.requestHttp = requestHttp;
        this.request = request;
    }
    public static AddListCommand from(HttpServletRequest requestHttp, AddListRequest request){
        return new AddListCommand(requestHttp,request);
    }

    @Override
    public ListIdResponce execute(ListService listService) {
        return listService.addList(request,requestHttp);
    }
}
