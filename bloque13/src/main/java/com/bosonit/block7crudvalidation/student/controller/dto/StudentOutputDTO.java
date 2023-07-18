package com.bosonit.block7crudvalidation.student.controller.dto;

import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosSimpleOutputDTO;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDTO {
    Integer id_student;
    PersonaOutputDTO persona;
    Integer num_hours_week;
    String coments;
    Integer id_profesor;
    String branch;
    List<EstudiosSimpleOutputDTO> estudios;

}
