package com.codetracking.progresstrackingapplication.entity;

import com.codetracking.progresstrackingapplication.enums.Difficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table ( name = "problems" )
public class Problem {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private long id;

    @Column (
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column (
            name = "description",
            nullable = false
    )
    private String description;

    @Column (
            name = "difficulty",
            nullable = false
    )
    private Difficulty difficulty;

    @ManyToMany (
            fetch = FetchType.LAZY
    )
    @JoinTable (
            name = "problem_topic_mapping",
            joinColumns = @JoinColumn (
                    name = "problem_id"
            ),
            inverseJoinColumns = @JoinColumn (
                name = "topic_id"
            )
    )
    private Set<Topic> relatedTopics;

}
