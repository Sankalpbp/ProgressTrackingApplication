package com.codetracking.progresstrackingapplication.enums;

public enum Role {

    ADMIN ( "Admin" ),
    STANDARD_USER ( "User" );

    private Role ( String name ) {
        this.name = name;
    }

    private final String name;

    public String getName ( ) {
        return this.name;
    }

}
