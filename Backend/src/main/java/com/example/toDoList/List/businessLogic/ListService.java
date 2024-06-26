package com.example.toDoList.List.businessLogic;

import com.example.toDoList.payload.request.AddListRequest;
import com.example.toDoList.payload.request.ChangeTitleListRequest;
import com.example.toDoList.payload.request.DeleteListRequest;
import com.example.toDoList.payload.response.ListIdResponce;
import com.example.toDoList.payload.response.ListResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface ListService {

    ListResponse getLists(HttpServletRequest request);

    boolean deleteList( long listId, HttpServletRequest httpRequest);

    ListIdResponce addList(AddListRequest request, HttpServletRequest requestHttp);
    boolean upDateListTitle(ChangeTitleListRequest request, HttpServletRequest requestHttp);


}
