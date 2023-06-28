package com.bosonit.block11uploaddownloadfiles.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EntityNotFoundException extends RuntimeException {
    CustomError error;

    public EntityNotFoundException(String mensaje) {
        super(mensaje);
        this.error = new CustomError(HttpStatus.NOT_FOUND.value(), mensaje);
    }
}
