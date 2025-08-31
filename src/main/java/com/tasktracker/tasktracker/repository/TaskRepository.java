package com.tasktracker.tasktracker.repository;

import com.tasktracker.tasktracker.dto.TaskDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskDto,Long> {

}
