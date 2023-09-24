package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.dto.ProblemDTO;
import com.codetracking.progresstrackingapplication.service.ProblemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( "/problems" )
public class ProblemController {

    private final ProblemService service;

    public ProblemController ( ProblemService service ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProblemDTO> addProblem (@RequestBody ProblemDTO problem ) {
        return new ResponseEntity<> ( service.addProblem ( problem ), HttpStatus.CREATED );
    }


}
