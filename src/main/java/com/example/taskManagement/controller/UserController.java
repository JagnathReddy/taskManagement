package com.example.taskManagement.controller;

import com.example.taskManagement.entity.Team;
import com.example.taskManagement.entity.User;
import com.example.taskManagement.repository.TeamRepository;
import com.example.taskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PasswordEncoder encoder;
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public User addUser(@RequestBody User user){
        System.out.println(user);
        String password=user.getPassword();
        user.setPassword(encoder.encode(password));
        return userRepository.save(user);
    }

    @RequestMapping(value = "/getUser/{id}",method = RequestMethod.GET)
    public User addUser(@PathVariable("id") Long id){
        System.out.println(userRepository.findById(id));
        return userRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/addTeam",method = RequestMethod.POST)
    public Team addTeam(@RequestBody() Team team,@RequestParam("members") Long[] members){
        for(Long i:members){
            try{
                User cur=userRepository.findById(i).orElseThrow();
                team.getUsers().add(cur);
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return teamRepository.save(team);
    }


    @RequestMapping(value = "/getTeam/{id}",method = RequestMethod.GET)
    public List<User> getTeam(@PathVariable("id") Long id){
        Team team=teamRepository.findById(id).orElseThrow();
        return team.getUsers();
    }



}
