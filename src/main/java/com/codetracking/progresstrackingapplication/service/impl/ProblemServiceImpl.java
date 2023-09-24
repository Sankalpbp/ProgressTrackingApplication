package com.codetracking.progresstrackingapplication.service.impl;

import com.codetracking.progresstrackingapplication.dto.ProblemDTO;
import com.codetracking.progresstrackingapplication.dto.TopicDTO;
import com.codetracking.progresstrackingapplication.entity.Problem;
import com.codetracking.progresstrackingapplication.entity.Topic;
import com.codetracking.progresstrackingapplication.exception.ResourceNotFoundException;
import com.codetracking.progresstrackingapplication.repository.ProblemRepository;
import com.codetracking.progresstrackingapplication.repository.TopicRepository;
import com.codetracking.progresstrackingapplication.service.ProblemService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ModelMapper mapper;

    private final ProblemRepository repository;

    private final TopicRepository topicRepository;

    public ProblemServiceImpl ( ModelMapper mapper,
                                ProblemRepository repository,
                                TopicRepository topicRepository ) {
        this.mapper = mapper;
        this.repository = repository;
        this.topicRepository = topicRepository;
    }

    @Override
    public ProblemDTO updateProblem ( ProblemDTO dto ) {
        Problem problem = repository.findByName ( dto.getName () )
                                    .orElseThrow ( () -> new ResourceNotFoundException ( "problem",
                                                                                         "name",
                                                                                         dto.getName () ) );
        Problem updatedProblem = mapToEntity ( dto );
        updatedProblem.setId ( problem.getId () );
        return mapToDTO ( repository.save ( updatedProblem ) );
    }

    @Override
    @Transactional
    public String deleteProblemByName ( String problemName ) {
        repository.deleteByName ( problemName );
        return "Problem deleted successfully!";
    }

    @Override
    public ProblemDTO getProblemByName ( String problemName ) {
        Problem problem = repository.findByName ( problemName )
                                    .orElseThrow ( () -> new ResourceNotFoundException ( "problem",
                                                                                         "name",
                                                                                         problemName ) );
        return mapToDTO ( problem );
    }

    @Override
    public List<ProblemDTO> getAllProblems () {
        return repository.findAll ().stream ()
                                    .map ( this :: mapToDTO )
                                    .toList ();
    }

    @Override
    public ProblemDTO addProblem ( ProblemDTO dto ) {
        Set<Topic> relatedTopics = dto.getRelatedTopics ().stream ()
                                                  .map ( TopicDTO :: getName )
                                                  .map ( topicName -> topicRepository.findByName ( topicName )
                                                                                     .orElseThrow ( () -> new ResourceNotFoundException ( "topic",
                                                                                                                                          "name",
                                                                                                                                          topicName ) ) )
                                                  .collect ( Collectors.toSet () );
        Problem problem = mapToEntity ( dto );
        problem.setRelatedTopics ( relatedTopics );
        return mapToDTO ( repository.save ( problem ) );
    }

    @Override
    public List<ProblemDTO> getProblemsByTopic ( String topicName ) {
        /* TODO: Complete the SQL query implementation */
        return new ArrayList<>();
    }

    private Problem mapToEntity ( ProblemDTO dto ) {
        Problem problem = mapper.map ( dto, Problem.class );
        problem.getRelatedTopics ().clear ();
        dto.getRelatedTopics ().stream ()
                               .map ( TopicDTO :: getName )
                               .map ( name -> topicRepository.findByName ( name )
                                                             .orElseThrow ( () -> new ResourceNotFoundException ( "topics",
                                                                                                                  "name",
                                                                                                                  name ) ) )
                               .forEach ( topic -> problem.getRelatedTopics ().add ( topic ) );
        return problem;
    }

    private ProblemDTO mapToDTO ( Problem problem ) {
        ProblemDTO dto = mapper.map ( problem, ProblemDTO.class );
        dto.setRelatedTopics ( dto.getRelatedTopics ().stream ()
                                                      .map ( topicDTO -> new TopicDTO ( topicDTO.getId (), topicDTO.getName () ) )
                                                      .collect ( Collectors.toSet () ) );
        return dto;
    }

}
