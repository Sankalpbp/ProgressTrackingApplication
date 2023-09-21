package com.codetracking.progresstrackingapplication.repository;

import com.codetracking.progresstrackingapplication.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
