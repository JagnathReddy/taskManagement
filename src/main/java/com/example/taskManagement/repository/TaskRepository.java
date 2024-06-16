package com.example.taskManagement.repository;

import com.example.taskManagement.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Long> {
}
