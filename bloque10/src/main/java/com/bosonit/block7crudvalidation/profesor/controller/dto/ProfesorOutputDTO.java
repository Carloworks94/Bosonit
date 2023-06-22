package com.bosonit.block7crudvalidation.profesor.controller.dto;

import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutputDTO {
    Integer idProfesor;
    PersonaOutputDTO persona;
    String comments;
    String branch;
}
