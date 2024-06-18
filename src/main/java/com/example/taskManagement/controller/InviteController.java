package com.example.taskManagement.controller;

import com.example.taskManagement.Service.UserInviteService;
import com.example.taskManagement.Service.UserService;
import com.example.taskManagement.UserDetails;
import com.example.taskManagement.entity.Company;
import com.example.taskManagement.entity.User;
import com.example.taskManagement.entity.UserInvite;
import com.example.taskManagement.pojos.AdminCompanyRegisterRequest;
import com.example.taskManagement.pojos.InviteLinkRegistrationRequest;
import com.example.taskManagement.repository.CompanyRepository;
import com.example.taskManagement.repository.UserInviteRepository;
import com.example.taskManagement.utils.MailingService;
import com.example.taskManagement.utils.enums.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class InviteController {
    @Autowired
    private UserInviteService userInviteService;


    @RequestMapping(value = "/invite",method = RequestMethod.POST)
    public ResponseEntity<?> newInvite(@RequestBody UserInvite invite){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(userInviteService.createInvite(invite));
    }

    @RequestMapping(value="/invite/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getInvite(@PathVariable("id") Long id){
        return userInviteService.getInvite(id);
    }

    @RequestMapping(value = "/invite/{id}",method = RequestMethod.POST)
    public ResponseEntity<?> registerViaInvite(@PathVariable("id") Long inviteId, @RequestBody InviteLinkRegistrationRequest req){
        return userInviteService.registerUserViaInvite(inviteId,req);
    }
}
