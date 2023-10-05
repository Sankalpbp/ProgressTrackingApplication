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
            "SELECT s " +
            "FROM Solution s INNER JOIN s.problem p INNER JOIN s.user u " +
            "WHERE p.name = :problemName " +
            "AND u.id = :userId"
    )
    public List<Solution> findByProblem ( String problemName, long userId );

    @Query (
            "SELECT s " +
            "FROM Solution s INNER JOIN s.user u " +
            "WHERE u.id = :userId"
    )
    public List<Solution> findByUser ( long userId );

}
