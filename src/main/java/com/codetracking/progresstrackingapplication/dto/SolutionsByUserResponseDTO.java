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
    private long pageNumber;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean last;

    public static Builder builder () {
        return new Builder ();
    }

    @Setter @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SolutionRecord {
        private long id;
        private Date time;
        private String languageUsed;
        private ProblemDTO problem;
    }

    public static class Builder {
        private List<SolutionRecord> solutions;
        private long userId;
        private String username;
        private long pageNumber;
        private int pageSize;
        private long totalElements;
        private long totalPages;
        private boolean last;

        private Builder () {

        }

        public Builder solutions ( List<SolutionRecord> solutions ) {
            this.solutions = solutions;
            return this;
        }

        public Builder userId ( long userId ) {
            this.userId = userId;
            return this;
        }
        public Builder username ( String username ) {
            this.username = username;
            return this;
        }
        public Builder pageNumber ( long pageNumber ) {
            this.pageNumber = pageNumber;
            return this;
        }
        public Builder pageSize ( int pageSize ) {
            this.pageSize = pageSize;
            return this;
        }
        public Builder totalElements ( long totalElements ) {
            this.totalElements = totalElements;
            return this;
        }
        public Builder totalPages ( long totalPages ) {
            this.totalPages = totalPages;
            return this;
        }
        public Builder last ( boolean last ) {
            this.last = last;
            return this;
        }

        public SolutionsByUserResponseDTO build () {
            SolutionsByUserResponseDTO pageResponse = new SolutionsByUserResponseDTO();
            pageResponse.solutions = solutions;
            pageResponse.userId = userId;
            pageResponse.username = username;
            pageResponse.pageNumber = pageNumber;
            pageResponse.pageSize = pageSize;
            pageResponse.totalElements = totalElements;
            pageResponse.totalPages = totalPages;
            pageResponse.last = last;

            return pageResponse;
        }

    }

}
