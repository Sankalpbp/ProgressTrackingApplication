package com.codetracking.progresstrackingapplication.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus ( value = HttpStatus.UNAUTHORIZED )
public class AuthenticationFailedException extends ApiException {

    private final String message;

    public AuthenticationFailedException ( String message ) {
        super ( HttpStatus.UNAUTHORIZED, message );
        this.message = message;
    }

}
