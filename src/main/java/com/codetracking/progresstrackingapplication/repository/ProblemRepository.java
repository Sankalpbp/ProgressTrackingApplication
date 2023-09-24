package com.codetracking.progresstrackingapplication.repository;

import com.codetracking.progresstrackingapplication.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
