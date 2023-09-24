package com.codetracking.progresstrackingapplication.service.impl;

import com.codetracking.progresstrackingapplication.dto.TopicDTO;
import com.codetracking.progresstrackingapplication.entity.Topic;
import com.codetracking.progresstrackingapplication.exception.ResourceNotFoundException;
import com.codetracking.progresstrackingapplication.repository.TopicRepository;
import com.codetracking.progresstrackingapplication.service.TopicService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;

    private final ModelMapper mapper;

    public TopicServiceImpl ( TopicRepository repository,
                              ModelMapper mapper ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public String deleteTopic ( String topicName ) {
        repository.deleteByName ( topicName );
        return "Topic Deleted successfully!";
    }

    public List<TopicDTO> getAllTopics ( ) {
        List<Topic> topics = repository.findAll ();
        return topics.stream ()
                     .map ( this :: mapToDTO )
                     .toList ();
    }

    @Override
    public TopicDTO getTopic ( String name ) {
        Topic topic = repository.findByName ( name )
                .orElseThrow ( () -> new ResourceNotFoundException ( "topic",
                                                                     "name",
                                                                     name ) );
        return mapToDTO ( topic );
    }

    @Override
    public TopicDTO addTopic ( TopicDTO topicDTO ) {
        Topic topic = mapToEntity ( topicDTO );
        return mapToDTO ( repository.save ( topic ) );
    }

    private Topic mapToEntity ( TopicDTO dto ) {
        return mapper.map ( dto, Topic.class );
    }

    private TopicDTO mapToDTO ( Topic topic ) {
        return mapper.map ( topic, TopicDTO.class );
    }

}
