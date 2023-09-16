package com.codetracking.progresstrackingapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<String> getAll () {
        return Arrays.asList ( "something", "the other thing" );
    }

}