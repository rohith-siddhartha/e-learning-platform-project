package com.classroom.LMS.security.service;

import com.classroom.LMS.security.Repository.UserCustomDetailsRepository;
import com.classroom.LMS.security.UserCustomDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCustomDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserCustomDetailsRepository userCustomDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCustomDetails userCustomDetails = this.userCustomDetailsRepository.findByUsername(username);

        if (userCustomDetails == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("No user found !!");
        }

        return userCustomDetails;
    }

    public UserCustomDetails save(UserCustomDetails userCustomDetails) {

        return this.userCustomDetailsRepository.save(userCustomDetails);

    };

}
