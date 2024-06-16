package com.example.taskManagement.Service;

//import com.example.taskManagement.entity.User;
import com.example.taskManagement.entity.User;
import com.example.taskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getUserRole()));
        return authorities;
    }
}

