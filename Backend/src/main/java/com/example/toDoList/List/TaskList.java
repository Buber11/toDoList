package com.example.toDoList.List;

import com.example.toDoList.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Entity()
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "task_list")
public class TaskList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Long listId;
    
    @Column(name = "list_title")
    private String listTitle;
    
    @OneToMany( mappedBy = "listId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new LinkedList<>();

    @JsonIgnore
    @Column(name = "user_id")
    private Long userId;

}
