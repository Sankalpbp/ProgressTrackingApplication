package com.codetracking.progresstrackingapplication.service;

import com.codetracking.progresstrackingapplication.dto.TopicDTO;

import java.util.List;

public interface TopicService {

    public TopicDTO addTopic ( TopicDTO topic );

    public TopicDTO getTopic ( String name );

    public List<TopicDTO> getAllTopics ( );

}
