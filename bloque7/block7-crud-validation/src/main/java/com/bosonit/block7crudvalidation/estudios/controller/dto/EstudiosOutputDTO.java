package com.bosonit.block7crudvalidation.estudios.controller.dto;

import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.student.domain.Student;

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
    Profesor profesor;
    List<Student> student;
    String asignatura;
    String comment;
    Date initialDate;
    Date finishDate;

    public EstudiosOutputDTO(int idStudy, Profesor profesor, String asignatura, String comment, Date initialDate, Date finishDate){
        this.idStudy = idStudy;
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.comment = comment;
        this.initialDate = initialDate;
        this.finishDate = finishDate;
    }
}
