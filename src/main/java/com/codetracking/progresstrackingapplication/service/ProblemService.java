package com.codetracking.progresstrackingapplication.service;

import com.codetracking.progresstrackingapplication.dto.ProblemDTO;

import java.util.List;

public interface ProblemService {

    public ProblemDTO addProblem ( ProblemDTO problem );

    public ProblemDTO getProblemByName ( String name );

    public List<ProblemDTO> getAllProblems ( );

    public String deleteProblemByName ( String name );

    public ProblemDTO updateProblem ( ProblemDTO problem );

    public List<ProblemDTO> getProblemsByTopic ( String topicName );

}
