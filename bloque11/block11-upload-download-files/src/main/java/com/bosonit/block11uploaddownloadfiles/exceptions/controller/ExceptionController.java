package com.bosonit.block11uploaddownloadfiles.exceptions.controller;

import com.bosonit.block11uploaddownloadfiles.exceptions.CustomError;
import com.bosonit.block11uploaddownloadfiles.exceptions.IllegalArgumentException;
import com.bosonit.block11uploaddownloadfiles.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomError> handleIllegalArgumentException(IllegalArgumentException exception){
        return new ResponseEntity<>(exception.getError(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException exception){
        return new ResponseEntity<>(exception.getError(), HttpStatus.NOT_FOUND);
    }
}
