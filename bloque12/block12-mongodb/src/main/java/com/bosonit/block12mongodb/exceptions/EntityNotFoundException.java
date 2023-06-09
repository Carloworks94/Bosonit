package com.bosonit.block12mongodb.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EntityNotFoundException extends RuntimeException {
    CustomError error;

    public EntityNotFoundException(String message) {
        super(message);
        this.error = new CustomError(HttpStatus.NOT_FOUND.value(), message);
    }
}
