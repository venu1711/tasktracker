package com.tasktracker.tasktracker.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Subtasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task parentTask;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedUser;
}
