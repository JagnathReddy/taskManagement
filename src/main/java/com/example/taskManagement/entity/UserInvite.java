package com.example.taskManagement.entity;

import com.example.taskManagement.utils.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String registerURL;
    private UserRoles role;
    private String password;
    @ManyToOne()
    @JsonIgnoreProperties("invites")
    private Company company;
}
