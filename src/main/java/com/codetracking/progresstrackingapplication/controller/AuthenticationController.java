package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.dto.SignInDTO;
import com.codetracking.progresstrackingapplication.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ( "/auth" )
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController ( AuthenticationService service ) {
        this.service = service;
    }

    @PostMapping ( "/signIn" )
    public SignInDTO signIn ( @RequestBody SignInDTO userDetails ) {
        /* TODO: put some validations */
        return service.signIn ( userDetails );
    }

}
