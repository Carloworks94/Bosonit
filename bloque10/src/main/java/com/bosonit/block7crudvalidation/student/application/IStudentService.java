package com.bosonit.block7crudvalidation.student.application;

import com.bosonit.block7crudvalidation.student.controller.dto.StudentInputDTO;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDTO;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDTO;

import java.util.List;

public interface IStudentService {
    StudentOutputDTO addStudent(StudentInputDTO studentInputDTO);

    StudentOutputDTO addEstudiosToStudent (int idEstudent, List<Integer> lEstudiosId);
    //StudentOutputDTO addPersonaToStudent(Persona persona) throws Exception;

    StudentOutputDTO getStudent (int id) throws Exception;

    StudentSimpleOutputDTO getSimpleStudent (int id) throws Exception;
    List<StudentOutputDTO> getAllStudents();

    StudentOutputDTO updateStudent (int id, StudentInputDTO studentInputDTO) throws Exception;

    StudentOutputDTO deleteStudent (int id) throws Exception;

    StudentOutputDTO deleteEstudiosToStudent (int idEstudent, List<Integer> lEstudiosId);
}
