package com.example.toDoList.List;

import com.example.toDoList.task.Task;
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
@Table(name = "list")
public class TaskList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Long listId;
    
    @Column(name = "list_title")
    private String listTitle;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "list_id")
    private List<Task> tasks = new LinkedList<>();
    
    @Column(name = "user_id")
    private Long userId;

}
