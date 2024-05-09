package com.example.toDoList.List.businessLogic;

import com.example.toDoList.List.ListRepository;
import com.example.toDoList.List.TaskList;
import com.example.toDoList.payload.response.ListResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListServiceImpl implements ListService{

    private final ListRepository listRepository;

    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public ListResponse getLists(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("id");
        List<TaskList> taskLists = listRepository.findAllByUserId(userId);
        System.out.println(taskLists);
        ListResponse response = new  ListResponse(taskLists);
        return response;
    }
}
