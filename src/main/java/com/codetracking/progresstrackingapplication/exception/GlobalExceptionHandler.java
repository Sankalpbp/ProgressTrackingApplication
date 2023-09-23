package com.codetracking.progresstrackingapplication.exception;

import com.codetracking.progresstrackingapplication.dto.ErrorDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler ( ApiException.class )
    public ResponseEntity<ErrorDetailsDTO> handleResourceNotFoundException ( ApiException exception,
                                                                             WebRequest request ) {
        return new ResponseEntity<> (
                new ErrorDetailsDTO ( new Date (),
                                      exception.getMessage (),
                                      request.getDescription ( false ) ),
                exception.getStatus ()
        );
    }

}
