package com.example.toDoList.List.businessLogic;

import com.example.toDoList.List.ListRepository;
import com.example.toDoList.List.TaskList;
import com.example.toDoList.payload.request.AddListRequest;
import com.example.toDoList.payload.request.ChangeTitleListRequest;
import com.example.toDoList.payload.request.DeleteListRequest;
import com.example.toDoList.payload.response.ListIdResponce;
import com.example.toDoList.payload.response.ListResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
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
        ListResponse response = new  ListResponse(taskLists);
        return response;
    }

    @Override
    public boolean deleteList( long listId, HttpServletRequest httpRequest) {
        Long userId = (long) httpRequest.getAttribute("id");
        if(listRepository.existsByListIdAndUserId(listId, userId)){
            Optional<TaskList> list = listRepository.findById(listId);
            listRepository.delete(list.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ListIdResponce addList(AddListRequest request, HttpServletRequest requestHttp) {
        long userId = (long) requestHttp.getAttribute("id");

        TaskList list = listRepository.save(TaskList.builder()
                .listTitle(request.listTitle())
                .userId(userId)
                .build()
        );


        return new ListIdResponce(list.getListId());

    }

    @Override
    @Transactional
    public boolean upDateListTitle(ChangeTitleListRequest request, HttpServletRequest requestHttp) {
        long userId = (long) requestHttp.getAttribute("id");
        if(listRepository.existsByListIdAndUserId(request.listId(), userId)){
            listRepository.setListTitleById(request.listTitle(),request.listId());
            return true;
        }else {
            return false;
        }
    }
}
