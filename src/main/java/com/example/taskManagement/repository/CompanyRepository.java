package com.example.taskManagement.repository;

import com.example.taskManagement.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
