package com.codetracking.progresstrackingapplication.dto;

import com.codetracking.progresstrackingapplication.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDTO {

    private long id;
    private String name;
    private String description;
    private Difficulty difficulty;
    private Set<TopicDTO> relatedTopics;

}
