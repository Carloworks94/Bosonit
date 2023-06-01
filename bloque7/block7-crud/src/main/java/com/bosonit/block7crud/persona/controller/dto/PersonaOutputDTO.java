package com.bosonit.block7crud.persona.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutputDTO {
    int id;
    String nombre;
    String edad;
    String poblacion;
}
