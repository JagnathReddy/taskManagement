package com.example.taskManagement.Service;

import com.example.taskManagement.UserDetails;
import com.example.taskManagement.entity.Company;
import com.example.taskManagement.entity.User;
import com.example.taskManagement.entity.UserInvite;
import com.example.taskManagement.pojos.InviteLinkRegistrationRequest;
import com.example.taskManagement.repository.UserInviteRepository;
import com.example.taskManagement.utils.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Service
public class UserInviteService {
    @Autowired
    private UserInviteRepository userInviteRepository;
    @Autowired
    private MailingService mailingService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;

    Random random =new Random();
    public UserInvite createInvite(UserInvite userInvite){
        UserDetails ud=(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company=ud.getCompany();
        String baseURL=userInvite.getRegisterURL();
        userInvite.setCompany(company);
        userInvite=userInviteRepository.save(userInvite);
        company.getInvites().add(userInvite);
        companyService.saveCompany(company);
        userInvite.setRegisterURL(baseURL+"/"+userInvite.getId().toString());
        userInvite.setPassword(Integer.toString(random.nextInt()));
        sendRegisterLink(userInvite.getEmail(),userInvite.getFirstName(),company.getName(),userInvite.getRegisterURL(),userInvite.getPassword());
        return userInviteRepository.save(userInvite);
    }

    public void sendRegisterLink(String email,String name,String companyName,String registerLink,String password){
        SimpleMailMessage smm=new SimpleMailMessage();
        smm.setFrom("noreply@taskflow.com");
        smm.setSubject(companyName+" register invitation on TaskFlow!");
        smm.setText("""
                Dear %s,
                
                %s invited you to join %s@TaskFlow.
                Use this link and given password to register yourself and get going!!
                Link : %s
                Password : %s
                """.formatted(name,companyName,companyName,registerLink,password));
        smm.setTo(email);
        System.out.println(smm);
        mailingService.getJavaMailSender().send(smm);
    }

    public ResponseEntity<?> getInvite(Long id){
        UserInvite invite=userInviteRepository.findById(id).orElse(null);
        if(invite==null)return ResponseEntity.notFound().build();
        invite.setPassword("");
        return ResponseEntity.ok().body(invite);
    }

    public ResponseEntity<?> registerUserViaInvite(Long inviteId, InviteLinkRegistrationRequest req){
        UserInvite userInvite=userInviteRepository.findById(inviteId).orElse(null);
        if(userInvite==null)return ResponseEntity.notFound().build();
        if(!userInvite.getPassword().equals(req.getUserInvite().getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user=req.getUser();
        user.setUserRole(userInvite.getRole());
        Long companyId=req.getUserInvite().getCompany().getCompanyId();
        Company company=companyService.getCompanyById(companyId).orElse(null);
        if(company==null)return ResponseEntity.notFound().build();
        userInviteRepository.deleteInvite(inviteId);
        user.setCompany(company);
        company.getEmployees().add(user);
        user=userService.newUser(user);
        company=companyService.saveCompany(company);
        return ResponseEntity.ok().body("Success");
    }
}
