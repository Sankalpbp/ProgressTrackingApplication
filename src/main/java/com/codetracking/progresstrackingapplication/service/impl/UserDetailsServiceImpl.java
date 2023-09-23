package com.codetracking.progresstrackingapplication.service.impl;

import com.codetracking.progresstrackingapplication.entity.User;
import com.codetracking.progresstrackingapplication.security.UserDetailsImpl;
import com.codetracking.progresstrackingapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /* email is what uniquely identifies the user */
    @Override
    public UserDetails loadUserByUsername ( String email ) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail ( email );
        return user.map ( UserDetailsImpl :: new )
                   .orElseThrow ( () -> new UsernameNotFoundException ( "user not found: " + email ) );
    }

}
