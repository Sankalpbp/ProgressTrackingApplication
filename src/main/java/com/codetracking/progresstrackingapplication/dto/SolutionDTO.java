package com.codetracking.progresstrackingapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class SolutionDTO {

    private long id;
    private Date time;
    private String languageUsed;
    private ProblemDTO problem;

}
