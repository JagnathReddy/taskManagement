package com.example.taskManagement.entity;

import jakarta.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;

    @ManyToOne(cascade = CascadeType.ALL)
    private User createdBy;
}
