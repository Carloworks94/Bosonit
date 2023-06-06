package com.bosonit.block7crudvalidation.persona.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CustomError {
    Date timeStamp;

    int httpCode;

    String mensaje;

    public CustomError(int httpCode, String mensaje) {
        this.httpCode = httpCode;
        this.mensaje = mensaje;
        this.timeStamp = new Date();
    }
}
