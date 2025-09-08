package com.tasktracker.tasktracker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String email;

    @ManyToMany(mappedBy = "assigned_users")
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "assigned_users",cascade = CascadeType.ALL)
    private List<SubTask> subTasks = new ArrayList<>();
}
