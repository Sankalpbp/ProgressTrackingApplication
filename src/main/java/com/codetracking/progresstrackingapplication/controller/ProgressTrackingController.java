package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.constants.ApiConstants;
import com.codetracking.progresstrackingapplication.entity.Solution;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ( "/progress" )
public class ProgressTrackingController {

    @GetMapping ( "/welcome" )
    public ResponseEntity<String> welcome ( ) {
        return ResponseEntity.ok (ApiConstants.WELCOME_MESSAGE);
    }

    @GetMapping ( "/getAll" )
    public List<Solution> getAll ( @AuthenticationPrincipal UserDetails userDetails ) {
        String email = userDetails.getUsername ();
        return List.of ( new Solution(), new Solution () );
    }

}
