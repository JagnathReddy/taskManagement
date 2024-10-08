package com.example.taskManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    private String name;
    private String address;
    private String description;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> employees;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "company",cascade = CascadeType.ALL)
    private List<UserInvite> invites = new ArrayList<>();



}
