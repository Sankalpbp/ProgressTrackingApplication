package com.codetracking.progresstrackingapplication.service;

import com.codetracking.progresstrackingapplication.dto.SolutionDTO;
import com.codetracking.progresstrackingapplication.dto.SolutionsCountDTO;

public interface ProgressTrackingService {

    public SolutionDTO addSolution ( String email, SolutionDTO solution );

    public SolutionsCountDTO getSolutionsCount ( String email, String problemName );

}
