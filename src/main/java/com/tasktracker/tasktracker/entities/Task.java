package com.tasktracker.tasktracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "task_id_seq")
    @SequenceGenerator(name = "task_id_seq", allocationSize = 1)
    private Long id;

    private String title;

    private String status; //to-do, in-progress, done

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )

    private List<User> assignedUsers = new ArrayList<>();

    @OneToMany(mappedBy = "parentTask",cascade = CascadeType.ALL)
    private List<Task> subTasks = new ArrayList<>();

}