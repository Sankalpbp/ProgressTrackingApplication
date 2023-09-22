package com.codetracking.progresstrackingapplication.controller;

import com.codetracking.progresstrackingapplication.dto.TopicDTO;
import com.codetracking.progresstrackingapplication.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ( "/topics" )
public class TopicController {

    private final TopicService service;

    public TopicController ( TopicService service ) {
        this.service = service;
    }

    /*
        TODO: This API shouldn't be publicly accessible.
         Any addition of topic / problem from the user should be taken as a request
         How?
         The simplest way I could think so far is to have only a single Admin,
         and this api would fail for any other user
    */
    @PostMapping
    public ResponseEntity<TopicDTO> addTopic (@RequestBody TopicDTO topic ) {
        return new ResponseEntity<>( service.addTopic ( topic ), HttpStatus.CREATED );
    }

}
