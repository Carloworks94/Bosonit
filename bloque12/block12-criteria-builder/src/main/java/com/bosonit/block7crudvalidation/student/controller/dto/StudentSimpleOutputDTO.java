package com.bosonit.block7crudvalidation.student.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSimpleOutputDTO {
    Integer id_student;
    Integer num_hours_week;
    String coments;
    Integer id_profesor;
    String branch;
}
