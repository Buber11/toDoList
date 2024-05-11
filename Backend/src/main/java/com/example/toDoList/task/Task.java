package com.example.toDoList.task;


import jakarta.persistence.*;
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
    private String taskTitle;

    private Boolean completed;

    @Transient
    private Date TimeStamp;

}