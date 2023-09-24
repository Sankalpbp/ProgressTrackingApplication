package com.codetracking.progresstrackingapplication.enums;

public enum Difficulty {

    EASY ( "Easy" ),
    MEDIUM ( "Medium" ),
    HARD ( "Hard" );

    private final String value;

    private Difficulty ( String value ) {
        this.value = value;
    }

    public String getValue () {
        return this.value;
    }

}
