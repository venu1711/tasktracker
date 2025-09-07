package com.tasktracker.tasktracker.mapper.impl;

import com.tasktracker.tasktracker.dto.TaskDto;
import com.tasktracker.tasktracker.entities.Task;
import com.tasktracker.tasktracker.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements Mapper<Task, TaskDto> {

    @Override
    public TaskDto mapTo(Task task) {
        if (task == null) {
            return null;
        }
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    @Override
    public Task mapFrom(TaskDto dto) {
        if (dto == null) {
            return null;
        }
        return Task.builder()
                .id(dto.getId()) // optional, usually skip this when persisting new tasks
                .title(dto.getTitle())
                .status(dto.getStatus())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
