package com.example.taskManagement.controller;

import com.example.taskManagement.repository.UserRepository;
import com.example.taskManagement.entity.User;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@ComponentScan
@RestController
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable(name = "id") Long id){
        return userRepository.findById(id).orElse(null);
    }

}
