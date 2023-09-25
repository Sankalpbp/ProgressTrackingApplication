package com.codetracking.progresstrackingapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class SolutionsResponseDTO {

    private List<SolutionRecord> solutions = new ArrayList<> ();
    private ProblemDTO relatedProblem;

    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SolutionRecord {
        private long id;
        private Date time;
        private String languageUsed;
    }

}
