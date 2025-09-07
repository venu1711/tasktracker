package com.tasktracker.tasktracker.controller;


import com.tasktracker.tasktracker.dto.TaskDto;
import com.tasktracker.tasktracker.entities.Task;
import com.tasktracker.tasktracker.mapper.Mapper;
import com.tasktracker.tasktracker.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaskController {
    private final TaskService taskService;
    Mapper<Task,TaskDto> taskMapper;

    public TaskController(TaskService taskService,Mapper<Task,TaskDto> taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping("/tasks")
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapFrom(taskDto);
        Task savedTask = taskService.createTask(task);
        return taskMapper.mapTo(savedTask);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") Long id){
        Optional<Task> foundTask = taskService.getTask(id);
        return foundTask.map(task -> {
            TaskDto taskDto = taskMapper.mapTo(task);
            return new ResponseEntity<>(taskDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
        Task task = taskMapper.mapFrom(taskDto);
        Task updatedTask = taskService.updateTask(id,task);
        return new ResponseEntity<>(taskMapper.mapTo(updatedTask),HttpStatus.OK);
    }
}
