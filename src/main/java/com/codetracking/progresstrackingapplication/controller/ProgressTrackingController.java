package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.entity.Solution;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping ( "/progress")
public class ProgressTrackingController {

    @GetMapping ( "/welcome" )
    public String welcome ( ) {
        return "Hey! Welcome! You can track your progress by signing in!";
    }

    @GetMapping ( "/getAll" )
    public List<Solution> getAll (@AuthenticationPrincipal UserDetails userDetails ) {
        String email = userDetails.getUsername ();
        return List.of ( new Solution(), new Solution () );
    }

}
