package com.tasktracker.tasktracker.services.impl;

import com.tasktracker.tasktracker.dto.TaskDto;
import com.tasktracker.tasktracker.repository.TaskRepository;
import com.tasktracker.tasktracker.services.TaskService;

import java.util.Optional;

public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepo;

    public TaskServiceImpl(TaskRepository taskRepo){
        this.taskRepo = taskRepo;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        return taskRepo.save(taskDto);
    }

    @Override
    public Optional<TaskDto> getTask(Long id) {
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
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        taskDto.setId(id);
        return taskRepo.findById(id).map(existingTask -> {
            Optional.ofNullable(taskDto.getTitle()).ifPresent(existingTask::setTitle);
            Optional.ofNullable(taskDto.getStatus()).ifPresent(existingTask::setStatus);
            return taskRepo.save(existingTask);
        }).orElseThrow(() -> new RuntimeException("Task does not exist"));
    }

}
