package com.bosonit.block7crudvalidation.profesor.controller.dto;

import com.bosonit.block7crudvalidation.persona.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutputDTO {
    Integer idProfesor;
    Persona persona;
    String comments;
    String branch;
}
