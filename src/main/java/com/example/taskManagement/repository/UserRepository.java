package com.example.taskManagement.repository;

import com.example.taskManagement.entity.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

@ComponentScan
public interface UserRepository extends CrudRepository<User,Long> {

}
