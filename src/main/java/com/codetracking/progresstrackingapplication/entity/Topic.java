package com.codetracking.progresstrackingapplication.entity;

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
@Table ( name = "topics" )
public class Topic {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private long id;

    @Column (
            name = "name",
            nullable = false
    )
    private String name;

    @ManyToMany ( mappedBy = "relatedTopics" )
    Set<Problem> problems;

}
