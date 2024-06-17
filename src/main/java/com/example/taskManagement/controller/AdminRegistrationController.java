package com.example.taskManagement.controller;

import com.example.taskManagement.Service.UserService;
import com.example.taskManagement.entity.Company;
import com.example.taskManagement.entity.User;
import com.example.taskManagement.pojos.AdminCompanyRegisterRequest;
import com.example.taskManagement.repository.CompanyRepository;
import com.example.taskManagement.repository.UserRepository;
import com.example.taskManagement.utils.enums.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AdminRegistrationController {


    @Autowired
    private UserService userService;
    @Autowired
    private CompanyRepository companyRepository;
    @RequestMapping(value = "/public/company",method = RequestMethod.POST)
    public ResponseEntity<?> addCompanyAndAdmin(@RequestBody AdminCompanyRegisterRequest req){
        User admin=req.getAdmin();
        Company company=req.getCompany();
        admin.setUserRole(UserRoles.valueOf("ADMIN"));
        admin=userService.newUser(admin);
        ArrayList<User> employee=new ArrayList<>();
        employee.add(admin);
        company=companyRepository.save(company);
        company.setEmployees(employee);
        admin.setCompany(company);
        company=companyRepository.save(company);
        admin=userService.saveUser(admin);
        return ResponseEntity.ok().body(new AdminCompanyRegisterRequest(admin,company));
    }
}
