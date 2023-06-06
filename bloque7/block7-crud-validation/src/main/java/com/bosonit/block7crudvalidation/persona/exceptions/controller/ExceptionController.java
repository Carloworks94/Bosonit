package com.bosonit.block7crudvalidation.persona.exceptions.controller;

import com.bosonit.block7crudvalidation.persona.exceptions.CustomError;
import com.bosonit.block7crudvalidation.persona.exceptions.EntityNotFoundException;
import com.bosonit.block7crudvalidation.persona.exceptions.UnprocessableEntityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException exception){
        return new ResponseEntity<>(exception.getError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(UnprocessableEntityException exception){
        return new ResponseEntity<>(exception.getError(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
