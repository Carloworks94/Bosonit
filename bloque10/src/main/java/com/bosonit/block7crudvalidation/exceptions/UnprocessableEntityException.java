package com.bosonit.block7crudvalidation.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UnprocessableEntityException extends RuntimeException{
    CustomError error;

    public UnprocessableEntityException (String message){
        super(message);
        this.error = new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), message);
    }
}
