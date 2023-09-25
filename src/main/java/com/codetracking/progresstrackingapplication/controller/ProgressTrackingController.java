package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.constants.ApiConstants;
import com.codetracking.progresstrackingapplication.dto.SolutionDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsCountDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsResponseDTO;
import com.codetracking.progresstrackingapplication.entity.Solution;
import com.codetracking.progresstrackingapplication.service.ProgressTrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "/progress" )
public class ProgressTrackingController {

    private final ProgressTrackingService service;

    public ProgressTrackingController ( ProgressTrackingService service ) {
        this.service = service;
    }

    @GetMapping ( "/welcome" )
    public ResponseEntity<String> welcome ( ) {
        return ResponseEntity.ok (ApiConstants.WELCOME_MESSAGE);
    }

    @PostMapping
    public ResponseEntity<SolutionDTO> addSolution ( @AuthenticationPrincipal UserDetails userDetails,
                                                     @RequestBody SolutionDTO solution ) {
        /* TODO: add validations for problem name  */
        return new ResponseEntity<> ( service.addSolution ( userDetails.getUsername (), solution ), HttpStatus.CREATED );
    }

    @GetMapping ( "/getSolutions" )
    public List<Solution> getSolutions ( @AuthenticationPrincipal UserDetails userDetails ) {
        String email = userDetails.getUsername ();
        return List.of ( new Solution(), new Solution () );
    }

    @GetMapping ( "/getSolutionsCount/{problemName}" )
    public ResponseEntity<SolutionsCountDTO> getSolutionsCount (@AuthenticationPrincipal UserDetails userDetails,
                                                                @PathVariable ( "problemName" ) String problemName ) {
        return ResponseEntity.ok ( service.getSolutionsCount ( userDetails.getUsername (), problemName ) );
    }

    @GetMapping ( "/{problemName}" )
    public ResponseEntity<SolutionsResponseDTO> getSolutions (@AuthenticationPrincipal UserDetails userDetails,
                                                              @PathVariable ( "problemName" ) String problemName ) {
        return ResponseEntity.ok ( service.getSolutions ( userDetails.getUsername (), problemName ) );
    }

}
