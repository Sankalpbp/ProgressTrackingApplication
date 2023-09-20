package com.codetracking.progresstrackingapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTO {

    private String name;

    private String email;

    private String password;

    private Date dateJoined;

}
