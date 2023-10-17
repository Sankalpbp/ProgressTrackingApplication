package com.codetracking.progresstrackingapplication.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class SolutionsByUserResponseDTO {

    private List<SolutionRecord> solutions = new ArrayList<>();
    private long userId;
    private String username;
    private long pageNumber;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean last;

    private SolutionsByUserResponseDTO () {

    }

    public static Builder builder () {
        return new Builder ();
    }

    @Getter
    public static class SolutionRecord {
        private long id;
        private Date time;
        private String languageUsed;
        private ProblemDTO problem;

        private SolutionRecord () {

        }

        public static Builder builder () {
            return new Builder ();
        }

        public static class Builder {

            private long id;
            private Date time;
            private String languageUsed;
            private ProblemDTO problem;

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
            public Builder problem ( ProblemDTO problem ) {
                this.problem = problem;
                return this;
            }

            public SolutionRecord build () {
                SolutionRecord record = new SolutionRecord();
                record.id = id;
                record.time = time;
                record.languageUsed = languageUsed;
                record.problem = problem;

                return record;
            }
        }
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
