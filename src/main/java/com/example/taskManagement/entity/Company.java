package com.example.taskManagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue
    private Long companyId;
    private String name;
    private String address;
    private String description;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<User> employees;
}
