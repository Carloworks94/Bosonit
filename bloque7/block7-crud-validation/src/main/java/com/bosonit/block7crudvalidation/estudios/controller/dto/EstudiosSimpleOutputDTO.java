package com.bosonit.block7crudvalidation.estudios.controller.dto;

import com.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosSimpleOutputDTO {
        Integer idStudy;
        String asignatura;
        String comment;
        Date initialDate;
        Date finishDate;
}
