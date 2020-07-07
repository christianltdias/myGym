package com.exercisetracker.services;

import com.exercisetracker.domain.User;
import com.exercisetracker.repositories.UserRepository;
import com.exercisetracker.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(email);
        }


        return new UserSS(user.getId(),user.getEmail(),user.getPassword(),user.getProfiles());
    }
    
}