package com.codetracking.progresstrackingapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table ( name = "solutions" )
public class Solution {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private long id;

    @Column (
            name = "time",
            nullable = false
    )
    private Date time;

    @Column (
            name = "languageUsed",
            nullable = false
    )
    private String languageUsed;

    @ManyToOne (
            fetch = FetchType.LAZY
    )
    @JoinColumn (
            name = "user_id",
            nullable = false
    )
    private User user;

    @ManyToOne (
            fetch = FetchType.EAGER
    )
    @JoinColumn (
            name = "problem_id",
            nullable = false
    )
    private Problem problem;
}
