package com.codetracking.progresstrackingapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
            nullable = false
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
    private String difficulty;

}
