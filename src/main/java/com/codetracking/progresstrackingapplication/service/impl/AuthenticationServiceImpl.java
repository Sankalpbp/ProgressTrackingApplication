package com.codetracking.progresstrackingapplication.service.impl;

import com.codetracking.progresstrackingapplication.dto.AuthDTO;
import com.codetracking.progresstrackingapplication.dto.LoginDTO;
import com.codetracking.progresstrackingapplication.dto.SignInDTO;
import com.codetracking.progresstrackingapplication.entity.User;
import com.codetracking.progresstrackingapplication.exception.AuthenticationFailedException;
import com.codetracking.progresstrackingapplication.exception.ResourceNotFoundException;
import com.codetracking.progresstrackingapplication.repository.UserRepository;
import com.codetracking.progresstrackingapplication.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    private final PasswordEncoder encoder;

    public AuthenticationServiceImpl ( UserRepository userRepository,
                                       ModelMapper mapper,
                                       PasswordEncoder encoder ) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public LoginDTO login ( LoginDTO userDetails ) {
        User user = userRepository.findByEmail ( userDetails.getEmail () )
                                  .orElseThrow ( () -> new AuthenticationFailedException (
                                                                String.format ( "email: %s, is not registered",
                                                                                userDetails.getEmail () ) ) );
        return ( LoginDTO ) mapToAuthDTO ( user, LoginDTO.class );
    }

    @Override
    public SignInDTO signIn ( SignInDTO userDetails ) {
        User user = mapToUser ( userDetails );
        user.setPassword ( encoder.encode ( userDetails.getPassword () ) );
        user.setDateJoined ( new Date() );
        return ( SignInDTO ) mapToAuthDTO ( userRepository.save ( user ), SignInDTO.class );
    }

    private User mapToUser ( SignInDTO userDetails ) {
        return mapper.map ( userDetails, User.class );
    }

    private AuthDTO mapToAuthDTO (User user, Class<?> clazz ) {
        return ( AuthDTO ) mapper.map ( user, clazz );
    }

}
