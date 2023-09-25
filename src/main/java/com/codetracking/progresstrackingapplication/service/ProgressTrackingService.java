package com.codetracking.progresstrackingapplication.service;

import com.codetracking.progresstrackingapplication.dto.SolutionDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsCountDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsResponseDTO;

import java.util.List;

public interface ProgressTrackingService {

    public SolutionDTO addSolution ( String email, SolutionDTO solution );

    public SolutionsCountDTO getSolutionsCount ( String email, String problemName );

    public SolutionsResponseDTO getSolutions (String email, String problemName );

}
