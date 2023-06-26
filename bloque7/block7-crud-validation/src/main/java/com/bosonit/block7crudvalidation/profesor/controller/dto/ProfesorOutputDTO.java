package com.bosonit.block7crudvalidation.profesor.controller.dto;

import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutputDTO {
    Integer idProfesor;
    PersonaOutputDTO persona;
    String comments;
    String branch;
}
