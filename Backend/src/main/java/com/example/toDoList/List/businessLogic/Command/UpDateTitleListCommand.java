package com.example.toDoList.List.businessLogic.Command;

import com.example.toDoList.Fasada.Command;
import com.example.toDoList.List.businessLogic.ListService;
import com.example.toDoList.payload.request.ChangeTitleListRequest;
import com.example.toDoList.payload.response.ListResponse;
import jakarta.servlet.http.HttpServletRequest;

public class UpDateTitleListCommand implements Command<Boolean, ListService> {

    private final HttpServletRequest requestHttp;
    private final ChangeTitleListRequest request;

    private UpDateTitleListCommand(HttpServletRequest requestHttp, ChangeTitleListRequest request) {
        this.requestHttp = requestHttp;
        this.request = request;
    }
    public static UpDateTitleListCommand from(HttpServletRequest requestHttp, ChangeTitleListRequest request){
        return new UpDateTitleListCommand(requestHttp,request);
    }

    @Override
    public Boolean execute(ListService listService) {
        return listService.upDateListTitle(request,requestHttp);
    }
}
