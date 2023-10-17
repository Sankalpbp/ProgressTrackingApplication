package com.codetracking.progresstrackingapplication.service.impl;

import com.codetracking.progresstrackingapplication.constants.ApiConstants;
import com.codetracking.progresstrackingapplication.dto.*;
import com.codetracking.progresstrackingapplication.entity.*;
import com.codetracking.progresstrackingapplication.exception.AuthenticationFailedException;
import com.codetracking.progresstrackingapplication.exception.ResourceNotFoundException;
import com.codetracking.progresstrackingapplication.repository.ProblemRepository;
import com.codetracking.progresstrackingapplication.repository.SolutionRepository;
import com.codetracking.progresstrackingapplication.repository.UserRepository;
import com.codetracking.progresstrackingapplication.service.ProgressTrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProgressTrackingServiceImpl implements ProgressTrackingService {

    private final ModelMapper mapper;

    private final UserRepository userRepository;

    private final ProblemRepository problemRepository;

    private final SolutionRepository repository;

    public ProgressTrackingServiceImpl ( ModelMapper mapper,
                                         UserRepository userRepository,
                                         ProblemRepository problemRepository,
                                         SolutionRepository repository ) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.repository = repository;
    }

    @Override
    public SolutionDTO addSolution ( String email, SolutionDTO dto ) {
        Solution solution = mapToEntity ( dto );
        solution.setTime ( new Date() );
        solution.setUser ( userRepository.findByEmail ( email )
                                         .orElseThrow ( () -> new ResourceNotFoundException ( "user",
                                                                                              "email",
                                                                                              email ) ) );
        solution.setProblem ( problemRepository.findByName ( dto.getProblem ().getName () )
                                               .orElseThrow ( () -> new ResourceNotFoundException ( "problems",
                                                                                                    "name",
                                                                                                    dto.getProblem ().getName () ) ) );
        return mapToDTO ( repository.save ( solution ) );
    }

    @Override
    public SolutionsByUserResponseDTO getAllSolutions ( String email,
                                                        int pageNumber,
                                                        int pageSize,
                                                        String sortDir ) {
        User user = userRepository.findByEmail ( email )
                                  .orElseThrow ( () -> new AuthenticationFailedException ( "email not found" ) );

        List<Solution> solutions = repository.findByUser ( user.getId (),
                                                           ApiConstants.ASC,
                                                    pageNumber * pageSize,
                                                           pageSize );
        int solutionsCount = repository.countByUser ( user.getId () );

        return SolutionsByUserResponseDTO.builder ()
                                         .solutions ( solutions.stream ()
                                                               .map ( this :: createUserSolutionRecord )
                                                               .toList () )
                                         .userId ( user.getId () )
                                         .username ( user.getEmail () )
                                         .pageNumber ( pageNumber )
                                         .pageSize ( pageSize )
                                         .totalElements ( solutionsCount )
                                         .totalPages ( solutionsCount / pageSize + 1 )
                                         .last ( ( solutionsCount / pageSize ) == pageNumber )
                                         .build ();
    }

    @Override
    public SolutionsOfProblemResponseDTO getSolutions (String email,
                                                       String problemName,
                                                       int pageNumber,
                                                       int pageSize,
                                                       String sortDir) {
        User user = userRepository.findByEmail ( email )
                                  .orElseThrow ( () -> new AuthenticationFailedException ( "email not found" ) );

        Problem problem = problemRepository.findByName ( problemName )
                                           .orElseThrow ( () -> new ResourceNotFoundException ( "problems",
                                                                                                "name",
                                                                                                problemName ) );
        int solutionsCount = repository.countByProblem ( problemName, user.getId () );

        List<Solution> solutions = repository.findByProblem ( problem.getId (),
                                                              user.getId () );
        List<Solution> solutionsForRequiredPage = solutions.subList ( pageNumber * pageSize, Math.min ( pageNumber * pageSize + pageSize, solutions.size () ) );

        return SolutionsOfProblemResponseDTO.builder ()
                                            .solutions ( solutionsForRequiredPage.stream ()
                                                                  .map ( this :: createProblemSolutionRecord )
                                                                  .toList () )
                                            .relatedProblem ( createProblemDTO ( new Solution () {
                                                @Override
                                                public Problem getProblem () {
                                                    return problem;
                                                }
                                            } ) )
                                            .pageNumber ( pageNumber )
                                            .pageSize ( pageSize )
                                            .totalElements ( solutionsCount )
                                            .totalPages ((long) Math.ceil ((double) solutionsCount / pageSize))
                                            .last ( ( solutionsCount / pageSize ) == pageNumber )
                                            .build ();
    }

    @Override
    public SolutionsCountDTO getSolutionsCount ( String email, String problemName ) {
        User user = userRepository.findByEmail ( email )
                                  .orElseThrow ( () -> new AuthenticationFailedException ( "email not found" ) );
        int solutionsCount = repository.countByProblem ( problemName, user.getId () );
        return new SolutionsCountDTO ( solutionsCount );
    }

    private Solution mapToEntity ( SolutionDTO dto ) {
        return mapper.map ( dto, Solution.class );
    }

    private SolutionDTO mapToDTO ( Solution solution ) {
        SolutionDTO dto = mapper.map ( solution, SolutionDTO.class );
        dto.setProblem ( createProblemDTO ( solution ) );
        return dto;
    }

    private ProblemDTO createProblemDTO ( Solution solution ) {
        ProblemDTO problemDTO = mapper.map ( solution.getProblem (), ProblemDTO.class );
        problemDTO.getRelatedTopics ().clear ();
        solution.getProblem ().getRelatedTopics ()
                .stream ()
                .map ( topic -> new TopicDTO ( topic.getId (), topic.getName () ) )
                .forEach ( topicDTO -> problemDTO.getRelatedTopics ().add ( topicDTO ) );
        return problemDTO;
    }

    private SolutionsOfProblemResponseDTO.SolutionRecord createProblemSolutionRecord (Solution solution ) {
        SolutionsOfProblemResponseDTO.SolutionRecord solutionRecord = new SolutionsOfProblemResponseDTO.SolutionRecord();
        solutionRecord.setId ( solution.getId () );
        solutionRecord.setTime ( solution.getTime () );
        solutionRecord.setLanguageUsed ( solution.getLanguageUsed () );
        return solutionRecord;
    }

    private SolutionsByUserResponseDTO.SolutionRecord createUserSolutionRecord ( Solution solution ) {
        SolutionsByUserResponseDTO.SolutionRecord solutionRecord = new SolutionsByUserResponseDTO.SolutionRecord();
        solutionRecord.setId ( solution.getId () );
        solutionRecord.setTime ( solution.getTime () );
        solutionRecord.setLanguageUsed ( solution.getLanguageUsed() );
        solutionRecord.setProblem ( createProblemDTO ( solution ) );
        return solutionRecord;
    }

}
