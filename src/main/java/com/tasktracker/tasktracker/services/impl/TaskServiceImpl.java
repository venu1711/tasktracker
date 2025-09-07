package com.tasktracker.tasktracker.services.impl;

import com.tasktracker.tasktracker.dto.TaskDto;
import com.tasktracker.tasktracker.entities.Task;
import com.tasktracker.tasktracker.repository.TaskRepository;
import com.tasktracker.tasktracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepo;

    public TaskServiceImpl(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepo.findById(id);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return taskRepo.existsById(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        task.setId(id);
        return taskRepo.findById(id).map(existingTask -> {
            Optional.ofNullable(task.getTitle()).ifPresent(existingTask::setTitle);
            Optional.ofNullable(task.getStatus()).ifPresent(existingTask::setStatus);
            Optional.ofNullable(task.getUpdatedAt()).ifPresent(existingTask::setUpdatedAt);
            Optional.ofNullable(task.getCreatedAt()).ifPresent(existingTask::setCreatedAt);
            return taskRepo.save(existingTask);
        }).orElseThrow(() -> new RuntimeException("Task does not exist"));
    }
}
