package com.bosonit;

public class InvalidLineFormatException extends RuntimeException{
    public InvalidLineFormatException (String mensaje){
        super(mensaje);
    }
}
