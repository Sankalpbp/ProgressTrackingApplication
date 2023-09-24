package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.dto.ProblemDTO;
import com.codetracking.progresstrackingapplication.service.ProblemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping ( "/{problemName}" )
    public ResponseEntity<ProblemDTO> getProblemByName (@PathVariable ( "problemName" ) String problemName ) {
        return ResponseEntity.ok ( service.getProblemByName ( problemName ) );
    }

    @GetMapping
    public ResponseEntity<List<ProblemDTO>> getAllProblems ( ) {
        return ResponseEntity.ok ( service.getAllProblems () );
    }

    @DeleteMapping ( "/{problemName}" )
    public ResponseEntity<String> deleteProblem ( @PathVariable ( "problemName" ) String problemName ) {
        return ResponseEntity.ok ( service.deleteProblemByName ( problemName ) );
    }

    @PutMapping
    public ResponseEntity<ProblemDTO> updateProblem ( @RequestBody ProblemDTO problem ) {
        /* TODO: put the validation that the name of problem must be present in the request */
        return ResponseEntity.ok ( service.updateProblem ( problem ) );
    }

    @GetMapping ( "/{topicName}" )
    public ResponseEntity<List<ProblemDTO>> getProblemsByTopic ( @PathVariable ( "topicName" ) String topicName ) {
        return ResponseEntity.ok ( service.getProblemsByTopic ( topicName ) );
    }

}
