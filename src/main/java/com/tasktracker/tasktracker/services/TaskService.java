package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.dto.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);

    Optional<TaskDto> getTask(Long id);

    void deleteTask(Long id);

    boolean isExists(Long id);

    TaskDto updateTask(Long id, TaskDto taskDto);
}
