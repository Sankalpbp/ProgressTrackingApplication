package com.codetracking.progresstrackingapplication.service.impl;

import com.codetracking.progresstrackingapplication.dto.ProblemDTO;
import com.codetracking.progresstrackingapplication.dto.TopicDTO;
import com.codetracking.progresstrackingapplication.entity.Problem;
import com.codetracking.progresstrackingapplication.entity.Topic;
import com.codetracking.progresstrackingapplication.exception.ResourceNotFoundException;
import com.codetracking.progresstrackingapplication.repository.ProblemRepository;
import com.codetracking.progresstrackingapplication.repository.TopicRepository;
import com.codetracking.progresstrackingapplication.service.ProblemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    private Problem mapToEntity ( ProblemDTO dto ) {
        return mapper.map ( dto, Problem.class );
    }

    private ProblemDTO mapToDTO ( Problem problem ) {
        ProblemDTO dto = mapper.map ( problem, ProblemDTO.class );
        dto.setRelatedTopics ( dto.getRelatedTopics ().stream ()
                                                      .map ( TopicDTO :: getName )
                                                      .map ( TopicDTO :: new )
                                                      .collect ( Collectors.toSet () ) );
        return dto;
    }

}
