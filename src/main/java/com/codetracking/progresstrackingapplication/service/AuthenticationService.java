package com.codetracking.progresstrackingapplication.service;

import com.codetracking.progresstrackingapplication.dto.LoginDTO;
import com.codetracking.progresstrackingapplication.dto.SignInDTO;

public interface AuthenticationService {

    public SignInDTO signIn ( SignInDTO userDetails );

    public LoginDTO login ( LoginDTO userDetails );

}
