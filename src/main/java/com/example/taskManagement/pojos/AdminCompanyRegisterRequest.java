package com.example.taskManagement.pojos;

import com.example.taskManagement.entity.Company;
import com.example.taskManagement.entity.User;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCompanyRegisterRequest {
    private User admin;
    private Company company;
}
