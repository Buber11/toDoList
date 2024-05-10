package com.example.toDoList.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<TaskList,Long> {

    List<TaskList> findAllByUserId(long userId);
    boolean existsByListIdAndUserId(long listId, long userId);

    @Modifying
    @Query("UPDATE TaskList l set l.listTitle = ?1 Where l.listId = ?2")
    void setListTitleById(String listTitle, long listId );

}
