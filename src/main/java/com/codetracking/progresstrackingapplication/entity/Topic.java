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
@Table ( name = "topics" )
public class Topic {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private long id;

    private String name;

}
