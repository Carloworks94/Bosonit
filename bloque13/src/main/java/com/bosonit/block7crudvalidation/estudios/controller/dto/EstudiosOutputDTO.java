package com.bosonit.block7crudvalidation.estudios.controller.dto;

import com.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "student")
public class EstudiosOutputDTO {
    Integer idStudy;
    Integer idProfesor;
    //@JsonIgnore
    List<StudentSimpleOutputDTO> student;
    String asignatura;
    String comment;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    Date initialDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    Date finishDate;

    public EstudiosOutputDTO(int idStudy, int idProfesor, String asignatura, String comment, Date initialDate, Date finishDate) {
        this.idStudy = idStudy;
        this.idProfesor = idProfesor;
        this.asignatura = asignatura;
        this.comment = comment;
        this.initialDate = initialDate;
        this.finishDate = finishDate;
    }
}
