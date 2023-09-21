package com.codetracking.progresstrackingapplication.dto;

import com.codetracking.progresstrackingapplication.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {

    private long id;

    private String name;

    private List<Problem> problems;

}
