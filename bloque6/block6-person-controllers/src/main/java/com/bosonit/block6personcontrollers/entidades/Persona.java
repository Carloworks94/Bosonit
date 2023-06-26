package com.bosonit.block6personcontrollers.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Persona {
    private String name;
    private String poblacion;
    private int edad;

    public Persona(String name) {
        this.name = name;
    }
}
