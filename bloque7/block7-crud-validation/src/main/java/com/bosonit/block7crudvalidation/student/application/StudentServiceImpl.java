package com.bosonit.block7crudvalidation.student.application;

import com.bosonit.block7crudvalidation.exceptions.IllegalArgumentException;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.block7crudvalidation.persona.repository.IPersonaRepository;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentInputDTO;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDTO;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDTO;
import com.bosonit.block7crudvalidation.student.domain.Student;
import com.bosonit.block7crudvalidation.student.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    IStudentRepository studentRepository;
    @Autowired
    IPersonaRepository personaRepository;

    @Override
    public StudentOutputDTO addStudent(StudentInputDTO studentInputDTO){
        Persona persona = personaRepository.findById(studentInputDTO.getId_persona()).orElseThrow(
                () -> new EntityNotFoundException("404 - La persona no existe")
        );
        //comprobamos que el id de la persona que nos pasan no esté asignada a otro estudiante, ya que es una relacion OneToOne
        if (studentRepository.findByPersona(persona) != null){ //FIXME: habrá que comprobar que no se un profesor en el repositorio de profesor, ya que una persona solo puede ser estudiante o profesor
            throw new IllegalArgumentException("400 - La persona ya está asignada a otro estudiante");
        }
        Student student = new Student(studentInputDTO);
        student.setPersona(persona);
        return studentRepository.save(student).studentToStudentOutputDTO();
    }


    @Override
    public StudentOutputDTO getStudent (int id) throws Exception {
        return studentRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("404 - El estudiante no existe"))
                .studentToStudentOutputDTO();
    }

    @Override
    public StudentSimpleOutputDTO getSimpleStudent (int id) throws Exception {
        return studentRepository.findById(id).orElseThrow(
                        ()-> new EntityNotFoundException("404 - El estudiante no existe"))
                .studentToStudentSimpleOutputDTO();
    }
    @Override
    public List<StudentOutputDTO> getAllStudents(){
        return studentRepository.findAll().stream().map(student -> student.studentToStudentOutputDTO()).toList();
    }

    @Override
    public StudentOutputDTO updateStudent (int id) throws Exception {
        studentRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("404 - El estudiante no existe")
        );
        return studentRepository.save(studentRepository.findById(id).get()).studentToStudentOutputDTO();
    }

    @Override
    public StudentOutputDTO deleteStudent (int id) throws Exception {
        Student student = studentRepository.findById(id).orElseThrow(
                ()-> new Exception("404 - El estudiante no existe")
        );
        studentRepository.deleteById(id);
        return student.studentToStudentOutputDTO();
    }
}
