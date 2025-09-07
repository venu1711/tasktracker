package com.tasktracker.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tasktracker.tasktracker.entities.Task;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

}
