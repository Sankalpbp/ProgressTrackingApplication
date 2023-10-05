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
public class SolutionsByUserResponseDTO {

    private List<SolutionRecord> solutions = new ArrayList<>();
    private long userId;
    private String username;

    @Setter @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SolutionRecord {
        private long id;
        private Date time;
        private String languageUsed;
        private ProblemDTO problem;
    }

}
