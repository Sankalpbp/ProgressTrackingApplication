package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.dto.LoginDTO;
import com.codetracking.progresstrackingapplication.dto.SignInDTO;
import com.codetracking.progresstrackingapplication.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SignInDTO> signIn (@RequestBody SignInDTO userDetails ) {
        /* TODO: put some validations */
        return new ResponseEntity<> ( service.signIn ( userDetails ), HttpStatus.CREATED );
    }

    @PostMapping ( "/login" )
    public ResponseEntity<LoginDTO> login ( @RequestBody LoginDTO userDetails ) {
        return ResponseEntity.ok ( service.login ( userDetails ) );
    }

}
