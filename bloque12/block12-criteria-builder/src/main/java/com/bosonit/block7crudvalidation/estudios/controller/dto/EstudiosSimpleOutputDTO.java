package com.bosonit.block7crudvalidation.estudios.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
