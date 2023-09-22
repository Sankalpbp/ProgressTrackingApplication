package com.codetracking.progresstrackingapplication.entity;

import com.codetracking.progresstrackingapplication.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table ( name = "users" )
public class User {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private long id;

    @Column (
            name = "name",
            nullable = false
    )
    private String name;

    @Column (
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column (
            name = "password",
            nullable = false
    )
    private String password;

    @Column (
            name = "dateJoined",
            nullable = false
    )
    private Date dateJoined;

    @Column (
            name = "role",
            nullable = false
    )
    private Role role;

    @OneToMany (
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Solution> solutions;

}
