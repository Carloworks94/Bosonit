package com.bosonit.block7crudvalidation.student.controller.dto;

import com.bosonit.block7crudvalidation.estudios.domain.Estudios;
import com.bosonit.block7crudvalidation.student.domain.Student;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDTO {
    Integer id_persona;
    Integer num_hours_week;
    @Size(min = 3, message = "Tamanio minimo de coments 3 letras")
    String coments;
    Integer id_profesor;
    String branch;
    List<Estudios> estudios;

    public Student studentInputDTOtoStudent(Student student) {
        student.setNum_hours_week(this.num_hours_week);
        student.setComents(this.coments);
        student.setBranch(this.branch);
        student.setEstudios(this.estudios);

        return student;
    }

}
