package com.codetracking.progresstrackingapplication.service;

import com.codetracking.progresstrackingapplication.dto.SolutionDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsByUserResponseDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsCountDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsOfProblemResponseDTO;

import java.util.List;

public interface ProgressTrackingService {

    public SolutionDTO addSolution ( String email, SolutionDTO solution );

    public SolutionsCountDTO getSolutionsCount ( String email, String problemName );

    public SolutionsOfProblemResponseDTO getSolutions (String email, String problemName );

    public SolutionsByUserResponseDTO getAllSolutions (String email );

}
