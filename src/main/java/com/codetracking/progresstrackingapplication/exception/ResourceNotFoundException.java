package com.codetracking.progresstrackingapplication.exception;

import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus ( value = HttpStatus.NOT_FOUND )
public class ResourceNotFoundException extends ApiException {

    private final String resourceName;
    private final String fieldName;
    private final String fieldValue;

    public ResourceNotFoundException ( String resourceName,
                                       String fieldName,
                                       String fieldValue ) {
        super ( HttpStatus.NOT_FOUND, String.format ( "%s not found with %s: %s",
                                                      resourceName,
                                                      fieldName,
                                                      fieldValue ) );
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
