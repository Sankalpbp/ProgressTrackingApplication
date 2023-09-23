package com.codetracking.progresstrackingapplication.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {

    private final HttpStatus status;

    public ApiException ( HttpStatus status,
                          String message ) {
        super ( message );
        this.status = status;
    }

    public HttpStatus getStatus () {
        return this.status;
    }

}
