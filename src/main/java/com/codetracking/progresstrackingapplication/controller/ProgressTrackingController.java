package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.constants.ApiConstants;
import com.codetracking.progresstrackingapplication.dto.SolutionDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsByUserResponseDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsCountDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsOfProblemResponseDTO;
import com.codetracking.progresstrackingapplication.exception.ApiException;
import com.codetracking.progresstrackingapplication.service.ProgressTrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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

    @GetMapping
    public ResponseEntity<SolutionsByUserResponseDTO> getSolutions (
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam (
                    value = "pageNumber", defaultValue = ApiConstants.DEFAULT_PAGE_NUMBER, required = false
            ) @Min ( 0 ) int pageNumber,
            @RequestParam (
                    value = "pageSize", defaultValue = ApiConstants.DEFAULT_PAGE_SIZE, required = false
            ) @Max ( 50 ) int pageSize,
            @RequestParam (
                    value = "sortDir", defaultValue = ApiConstants.DEFAULT_SORT_BY_DIRECTION, required = false
            ) String sortDir
    ) {
        /* The solutions can only be sorted on the basis of the time when they were submitted,
           so no point of sortBy field */
        service.validate ( sortDir );
        String email = userDetails.getUsername ();
        return ResponseEntity.ok ( service.getAllSolutions ( email, pageNumber, pageSize, sortDir ) );
    }

    @GetMapping ( "/getSolutionsCount/{problemName}" )
    public ResponseEntity<SolutionsCountDTO> getSolutionsCount (@AuthenticationPrincipal UserDetails userDetails,
                                                                @PathVariable ( "problemName" ) String problemName ) {
        return ResponseEntity.ok ( service.getSolutionsCount ( userDetails.getUsername (), problemName ) );
    }

    @GetMapping ( "/{problemName}" )
    public ResponseEntity<SolutionsOfProblemResponseDTO> getSolutions (@AuthenticationPrincipal UserDetails userDetails,
                                                                       @PathVariable ( "problemName" ) String problemName ) {
        /* TODO: This method must have some sorting and pagination */
        return ResponseEntity.ok ( service.getSolutions ( userDetails.getUsername (), problemName ) );
    }

}
