package com.bosonit.block7crudvalidation.estudios.controller.dto;

import com.bosonit.block7crudvalidation.estudios.domain.Estudios;
import com.bosonit.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosInputDTO {
    //Integer idProfesor;
    Integer idStudent;
    String asignatura;
    String comment;
    Date initialDate;
    Date finishDate;

    public Estudios estudiosInputDTOToEstudios (Estudios estudios){
        estudios.setAsignatura(this.asignatura);
        estudios.setComment(this.comment);
        estudios.setInitialDate(this.initialDate);
        estudios.setFinishDate(this.finishDate);
        return estudios;
    }
}
