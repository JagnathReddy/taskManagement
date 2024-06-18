package com.example.taskManagement.entity;


import com.example.taskManagement.utils.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    @Column(unique = true)

    private String username;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private UserRoles userRole;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "createdBy")
    private List<Project> projectsCreated;

    @ManyToMany(mappedBy = "users")
    @JsonIgnoreProperties("users")
    private List<Team> teamList=new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("employees")
    private Company company;
}
