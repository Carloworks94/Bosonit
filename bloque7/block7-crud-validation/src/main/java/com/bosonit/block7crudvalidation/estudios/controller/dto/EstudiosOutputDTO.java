package com.bosonit.block7crudvalidation.estudios.controller.dto;

import com.bosonit.block7crudvalidation.student.domain.Student;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosOutputDTO {
    Integer idStudy;
//    Profesor profesor;
    Student student;
    String asignatura;
    String comment;
    Date initialDate;
    Date finishDate;
}
