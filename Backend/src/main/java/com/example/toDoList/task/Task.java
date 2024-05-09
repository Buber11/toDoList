package com.example.toDoList.task;

import com.example.toDoList.User.User;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    //we use this in delete method to check if userId from token is the same like userId owner
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "list_id")
    private Long listId;

    @Column(name = "task_title")
    private String titleTask;

    private Boolean completed;

    @Transient
    private Date TimeStamp;

}