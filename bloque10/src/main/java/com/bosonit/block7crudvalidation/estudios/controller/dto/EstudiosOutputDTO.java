package com.bosonit.block7crudvalidation.estudios.controller.dto;

import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDTO;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDTO;
import com.bosonit.block7crudvalidation.student.domain.Student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosOutputDTO {
    Integer idStudy;
    Integer idProfesor;
    //@JsonIgnore
    List<StudentSimpleOutputDTO> student;
    String asignatura;
    String comment;
    Date initialDate;
    Date finishDate;

    public EstudiosOutputDTO(int idStudy, int idProfesor, String asignatura, String comment, Date initialDate, Date finishDate){
        this.idStudy = idStudy;
        this.idProfesor = idProfesor;
        this.asignatura = asignatura;
        this.comment = comment;
        this.initialDate = initialDate;
        this.finishDate = finishDate;
    }
}
