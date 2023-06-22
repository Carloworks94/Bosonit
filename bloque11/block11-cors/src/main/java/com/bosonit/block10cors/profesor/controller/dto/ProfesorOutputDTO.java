package com.bosonit.block10cors.profesor.controller.dto;

import com.bosonit.block10cors.persona.controller.dto.PersonaOutputDTO;
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
