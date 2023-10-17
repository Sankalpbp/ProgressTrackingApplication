package com.codetracking.progresstrackingapplication.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class SolutionsOfProblemResponseDTO {

    private List<SolutionRecord> solutions = new ArrayList<> ();
    private ProblemDTO relatedProblem;
    private long pageNumber;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean last;

    private SolutionsOfProblemResponseDTO () {

    }

    public static Builder builder () {
        return new Builder ();
    }

    @Getter
    public static class SolutionRecord {
        private long id;
        private Date time;
        private String languageUsed;

        private SolutionRecord () {

        }

        public static Builder builder () {
            return new Builder ();
        }

        public static class Builder {

            private long id;
            private Date time;
            private String languageUsed;

            private Builder () {

            }

            public Builder id ( long id ) {
                this.id = id;
                return this;
            }

            public Builder time ( Date time ) {
                this.time = time;
                return this;
            }

            public Builder languageUsed ( String languageUsed ) {
                this.languageUsed = languageUsed;
                return this;
            }

            public SolutionRecord build () {
                SolutionRecord record = new SolutionRecord();
                record.id = id;
                record.time = time;
                record.languageUsed = languageUsed;
                return record;
            }

        }

    }

    public static class Builder {
        private List<SolutionRecord> solutions;
        private ProblemDTO relatedProblem;
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

        public Builder relatedProblem ( ProblemDTO relatedProblem ) {
            this.relatedProblem = relatedProblem;
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

        public SolutionsOfProblemResponseDTO build () {
            SolutionsOfProblemResponseDTO pageResponse = new SolutionsOfProblemResponseDTO();
            pageResponse.solutions = solutions;
            pageResponse.relatedProblem = relatedProblem;
            pageResponse.pageNumber = pageNumber;
            pageResponse.pageSize = pageSize;
            pageResponse.totalElements = totalElements;
            pageResponse.totalPages = totalPages;
            pageResponse.last = last;

            return pageResponse;
        }

    }

}
