package com.bosonit.block10cors.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class IllegalArgumentException extends RuntimeException{
    CustomError error;

    public IllegalArgumentException (String mensaje){
        super(mensaje);
        this.error = new CustomError(HttpStatus.BAD_REQUEST.value(), mensaje);
    }
}
