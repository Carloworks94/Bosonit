package com.bosonit.block7crudvalidation.profesor.controller.dto;

import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorInputDTO {
    Integer idPersona;
    String coments;
    String branch;

    public Profesor profesorInputDTOtoProfesor(Profesor profesor){
        profesor.setComents(this.coments);
        profesor.setBranch(this.branch);
        return profesor;
    }

}
