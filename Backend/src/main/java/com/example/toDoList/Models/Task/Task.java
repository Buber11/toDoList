package com.example.toDoList.Models.Task;

import com.example.toDoList.Models.User.User;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "task_title")
    private String titleTask;

    private Boolean complited;

    @Transient
    private Date TimeStamp;

}