package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.dto.TopicDTO;
import com.codetracking.progresstrackingapplication.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "/topics" )
public class TopicController {

    private final TopicService service;

    public TopicController ( TopicService service ) {
        this.service = service;
    }

    /*
        TODO: add, and delete, APIs shouldn't be publicly accessible.
         Any addition of topic / problem from the user should be taken as a request
         How?
         The simplest way I could think so far is to have only a single Admin,
         and this api would fail for any other user
    */
    @PostMapping
    public ResponseEntity<TopicDTO> addTopic ( @RequestBody TopicDTO topic ) {
        return new ResponseEntity<>( service.addTopic ( topic ), HttpStatus.CREATED );
    }

    @GetMapping ( "/{topicName}" )
    public ResponseEntity<TopicDTO> getTopic ( @PathVariable ( "topicName" ) String topicName ) {
        return ResponseEntity.ok ( service.getTopic ( topicName ) );
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAllTopics ( ) {
        return ResponseEntity.ok ( service.getAllTopics ( ) );
    }

    @DeleteMapping ( "/{topicName}" )
    public ResponseEntity<String> deleteTopic ( @PathVariable ( "topicName" ) String topicName ) {
        return ResponseEntity.ok ( service.deleteTopic ( topicName ) );
    }

}
