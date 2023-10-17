package com.codetracking.progresstrackingapplication.repository;

import com.codetracking.progresstrackingapplication.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    @Query (
            "SELECT COUNT ( s.id ) " +
            "FROM Solution s INNER JOIN s.problem p INNER JOIN s.user u " +
            "WHERE p.name = :problemName " +
            "AND u.id = :userId"
    )
    public int countByProblem ( String problemName, long userId );

    @Query (
            value = "SELECT new Solution ( s.id, time, languageUsed ) " +
                    "FROM Solution s " +
                    "INNER JOIN s.problem p " +
                    "INNER JOIN s.user u " +
                    "WHERE p.id = :problemId " +
                    "AND u.id = :userId"
    )
    public List<Solution> findByProblem ( long problemId, long userId );

    @Query (
            value = "SELECT s.id, time, user_id, problem_id, language_used " +
                    "FROM solutions AS s " +
                    "INNER JOIN users AS u " +
                    "WHERE u.id = :userId " +
                    "ORDER BY CASE WHEN :sortDir = 'ASC' THEN s.time END ASC, " +
                    "         CASE WHEN :sortDir = 'DESC' THEN s.time END DESC " +
                    "LIMIT :offset, :pageSize",
            nativeQuery = true
    )
    public List<Solution> findByUser ( long userId, String sortDir, int offset, int pageSize );

    @Query (
            value = "SELECT COUNT(s.id) " +
                    "FROM solutions as s " +
                    "INNER JOIN users AS u " +
                    "WHERE u.id = :userId ",
            nativeQuery = true
    )
    public int countByUser ( long userId );

}
