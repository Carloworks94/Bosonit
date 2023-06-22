package com.bosonit.block10cors.profesor.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorSimpleOutputDTO {
    Integer idProfesor;
    String comments;
    String branch;

}
