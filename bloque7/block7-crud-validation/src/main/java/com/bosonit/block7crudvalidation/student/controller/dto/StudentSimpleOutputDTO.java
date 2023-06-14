package com.bosonit.block7crudvalidation.student.controller.dto;

import com.bosonit.block7crudvalidation.estudios.domain.Estudios;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSimpleOutputDTO {
    Integer id_student;
    Integer num_hours_week;
    String coments;
    Profesor profesor;
    String branch;
    List<Estudios> estudios;

}
