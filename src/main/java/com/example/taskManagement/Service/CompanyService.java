package com.example.taskManagement.Service;

import com.example.taskManagement.entity.Company;
import com.example.taskManagement.entity.User;
import com.example.taskManagement.repository.CompanyRepository;
import com.example.taskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }

    public List<User> getAllCompanyUsers(Long id){
        Company company=companyRepository.findById(id).orElse(null);
        if(company==null)return null;
        return company.getEmployees();
    }
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id){
        return companyRepository.findById(id);
    }

    public Company saveCompany(Company company){
        return companyRepository.save(company);
    }

    public void deleteCompanyById(Long id){
            companyRepository.deleteById(id);
    }
}
