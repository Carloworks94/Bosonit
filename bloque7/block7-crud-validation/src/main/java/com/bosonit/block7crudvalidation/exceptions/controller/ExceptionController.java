package com.bosonit.block7crudvalidation.exceptions.controller;

import com.bosonit.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.bosonit.block7crudvalidation.exceptions.CustomError;
import com.bosonit.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.block7crudvalidation.exceptions.IllegalArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException exception){
        return new ResponseEntity<>(exception.getError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> handleUnprocessableEntityException(UnprocessableEntityException exception){
        return new ResponseEntity<>(exception.getError(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomError> handleIllegalArgumentException(IllegalArgumentException exception){
        return new ResponseEntity<>(exception.getError(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptionsController(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
