package com.codetracking.progresstrackingapplication.service;

import com.codetracking.progresstrackingapplication.constants.ApiConstants;
import com.codetracking.progresstrackingapplication.dto.SolutionDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsByUserResponseDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsCountDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsOfProblemResponseDTO;
import com.codetracking.progresstrackingapplication.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public interface ProgressTrackingService {

    public SolutionDTO addSolution ( String email, SolutionDTO solution );

    public SolutionsCountDTO getSolutionsCount ( String email, String problemName );

    public SolutionsOfProblemResponseDTO getSolutions (String email, String problemName );

    public SolutionsByUserResponseDTO getAllSolutions ( String email, int pageNumber, int pageSize, String sortDir );

    default void validate ( String sortDir ) {
        if ( !Arrays.asList ( ApiConstants.ASC, ApiConstants.DESC ).contains ( sortDir ) ) {
            throw new ApiException( HttpStatus.BAD_REQUEST, "Please provide valid direction for sorting" ) {};
        }
    }

}
