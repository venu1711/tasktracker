package com.tasktracker.tasktracker.controller;


import com.tasktracker.tasktracker.dto.TaskDto;
import com.tasktracker.tasktracker.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @GetMapping("/tasks/{id}")
    public Optional<TaskDto> getTask(@PathVariable("id") Long id){
        return taskService.getTask(id);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable("id") Long id){
        if(!taskService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long id, @RequestBody TaskDto taskDto){
        if(!taskService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TaskDto updatedTask = taskService.updateTask(id, taskDto);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
}
