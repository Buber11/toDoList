package com.example.toDoList.List.businessLogic;

import com.example.toDoList.payload.request.ListRequest;
import com.example.toDoList.payload.response.ListResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ListService {

    ListResponse getLists(HttpServletRequest request);

    boolean deleteList(ListRequest request, HttpServletRequest httpRequest);


}
