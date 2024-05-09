package com.example.toDoList.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<TaskList,Long> {

    List<TaskList> findAllByUserId(long userId);
    boolean existsByListIdAndUserId(long listId, long userId);
}
