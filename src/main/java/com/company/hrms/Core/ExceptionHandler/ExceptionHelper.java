package com.company.hrms.Core.ExceptionHandler;

import com.company.hrms.Core.Utilities.Result.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ResponseEntity<?> handleInvalidInputException(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResult(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
