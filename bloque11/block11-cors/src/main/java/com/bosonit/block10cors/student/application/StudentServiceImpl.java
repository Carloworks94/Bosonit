package com.bosonit.block10cors.student.application;

import com.bosonit.block10cors.estudios.domain.Estudios;
import com.bosonit.block10cors.estudios.repository.IEstudiosRepository;
import com.bosonit.block10cors.exceptions.EntityNotFoundException;
import com.bosonit.block10cors.exceptions.IllegalArgumentException;
import com.bosonit.block10cors.persona.domain.Persona;
import com.bosonit.block10cors.persona.repository.IPersonaRepository;
import com.bosonit.block10cors.profesor.domain.Profesor;
import com.bosonit.block10cors.profesor.repository.IProfesorRepository;
import com.bosonit.block10cors.student.controller.dto.StudentInputDTO;
import com.bosonit.block10cors.student.controller.dto.StudentOutputDTO;
import com.bosonit.block10cors.student.controller.dto.StudentSimpleOutputDTO;
import com.bosonit.block10cors.student.domain.Student;
import com.bosonit.block10cors.student.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    IStudentRepository studentRepository;
    @Autowired
    IPersonaRepository personaRepository;
    @Autowired
    IProfesorRepository profesorRepository;
    @Autowired
    IEstudiosRepository estudiosRepository;

    @Override
    public StudentOutputDTO addStudent(StudentInputDTO studentInputDTO) {
        Persona persona = personaRepository.findById(studentInputDTO.getId_persona()).orElseThrow(
                () -> new EntityNotFoundException("404 - La persona no existe")
        );
        Profesor profesor = profesorRepository.findById(studentInputDTO.getId_profesor()).orElseThrow(
                () -> new EntityNotFoundException("404 - El profesor no existe")
        );
        //comprobamos que el id de la persona que nos pasan no esté asignada a otro estudiante o profesor, ya que es una relacion OneToOne
        if (studentRepository.findByPersona(persona) != null || profesorRepository.findByPersona(persona) != null) { //FIXME: habrá que comprobar que no sea un profesor en el repositorio de profesor, ya que una persona solo puede ser estudiante o profesor
            throw new IllegalArgumentException("400 - La persona ya está asignada a otro estudiante/profesor");
        }
        Student student = new Student(studentInputDTO);
        student.setPersona(persona);
        student.setProfesor(profesor);
        return studentRepository.save(student).studentToStudentOutputDTO();
    }

    @Override
    public StudentOutputDTO addEstudiosToStudent(int idStudent, List<Integer> lEstudiosId) {
        Student student = studentRepository.findById(idStudent).orElseThrow(
                () -> new EntityNotFoundException("404 - No existe el estudiante")
        );

        for (Integer id : lEstudiosId) {
            Estudios estudios = estudiosRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("404 - Estudios no encontrados")
            );
            if (!student.getEstudios().contains(estudios)) {
                estudios.getStudents().add(student);
                student.getEstudios().add(estudios);
            }
        }

        return studentRepository.save(student).studentToStudentOutputDTO();
    }

    @Override
    public StudentOutputDTO getStudent(int id) throws Exception {
        return studentRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("404 - El estudiante no existe"))
                .studentToStudentOutputDTO();
    }

    @Override
    public StudentSimpleOutputDTO getSimpleStudent(int id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - El estudiante no existe"));
        //System.out.println(student.getProfesor());
//        Hibernate.initialize(student);
//        StudentSimpleOutputDTO studentSimpleOutputDTO = IStudentMapper.INSTANCE.studentToStudentSimpleOutputDTO(student);

        StudentSimpleOutputDTO studentSimpleOutputDTO = student.studentToStudentSimpleOutputDTO();
        //studentSimpleOutputDTO.setProfesor(studentRepository.findProfesorByStudentId(student.getId_student()));
        return studentSimpleOutputDTO;
    }

    @Override
    public List<StudentOutputDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(student -> student.studentToStudentOutputDTO()).toList();
    }


    @Override
    //FIXME: hay redundancia de datos, ya que suponemos que en el input siempre nos van a pasar el id, pero lo lógico es que no sea así
    public StudentOutputDTO updateStudent(int id, StudentInputDTO studentInputDTO) {
        Student studentAux = studentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - El estudiante no existe")
        );
        Persona persona = personaRepository.findById(studentInputDTO.getId_persona()).orElseThrow(
                () -> new EntityNotFoundException("404 - La persona no existe")
        );
        Profesor profesor = profesorRepository.findById(studentInputDTO.getId_profesor()).orElseThrow(
                () -> new EntityNotFoundException("404 - El profesor no existe")
        );
        if ((profesorRepository.findByPersona(persona) != null || studentRepository.findByPersona(persona) != null)
                && studentAux.getPersona().getId() != studentInputDTO.getId_persona()) {
            throw new IllegalArgumentException("400 - La persona ya está asignada a otro estudiante/profesor");
        }
        Student student = new Student(studentInputDTO);
        student.setId_student(id);
        student.setPersona(persona);
        student.setProfesor(profesor);
        return studentRepository.save(student).studentToStudentOutputDTO();
    }

    @Override
    public StudentOutputDTO deleteEstudiosToStudent(int idStudent, List<Integer> lEstudiosId) {
        Student student = studentRepository.findById(idStudent).orElseThrow(
                () -> new EntityNotFoundException("404 - No existe el estudiante")
        );
        for (Integer id : lEstudiosId) {
            Estudios estudios = estudiosRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("404 - Estudios no encontrados")
            );
            if(student.getEstudios().contains(estudios))
                student.getEstudios().remove(estudios);
        }

        return studentRepository.save(student).studentToStudentOutputDTO();
    }

    @Override
    public StudentOutputDTO deleteStudent(int id) throws Exception {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new Exception("404 - El estudiante no existe")
        );
        studentRepository.deleteById(id);
        return student.studentToStudentOutputDTO();
    }
}
