package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.dto.TaskDto;
import com.tasktracker.tasktracker.entities.Task;

import java.util.Optional;


public interface TaskService {
    Task createTask(Task task);

    Optional<Task> getTask(Long id);

    void deleteTask(Long id);

    boolean isExists(Long id);

    Task updateTask(Long id, Task task);
}
