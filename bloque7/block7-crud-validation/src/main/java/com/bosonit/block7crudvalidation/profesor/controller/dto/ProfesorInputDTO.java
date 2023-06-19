package com.bosonit.block7crudvalidation.profesor.controller.dto;

import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorInputDTO {
    Integer idPersona;
    List<Student> students;
    String coments;
    String branch;

    public Profesor profesorInputDTOtoProfesor(Profesor profesor){
        profesor.setComents(this.coments);
        profesor.setBranch(this.branch);
        this.students=new ArrayList<>();
        return profesor;
    }

}
