package com.example.toDoList.List.businessLogic;

import com.example.toDoList.List.ListRepository;
import com.example.toDoList.List.TaskList;
import com.example.toDoList.payload.request.ListRequest;
import com.example.toDoList.payload.response.ListResponse;
import com.example.toDoList.task.Task;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean deleteList(ListRequest request, HttpServletRequest httpRequest) {
        Long userId = (long) httpRequest.getAttribute("id");
        if(listRepository.existsByListIdAndUserId(request.listId(), userId)){
            Optional<TaskList> list = listRepository.findById(request.listId());
            listRepository.delete(list.get());
            return true;
        } else {
            return false;
        }
    }
}
