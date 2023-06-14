package com.bosonit.block7crudvalidation.estudios.application;

import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block7crudvalidation.estudios.domain.Estudios;
import com.bosonit.block7crudvalidation.estudios.repository.IEstudiosRepository;
import com.bosonit.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.block7crudvalidation.exceptions.IllegalArgumentException;
import com.bosonit.block7crudvalidation.profesor.application.IProfesorService;
import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.profesor.repository.IProfesorRepository;
import com.bosonit.block7crudvalidation.student.application.IStudentService;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDTO;
import com.bosonit.block7crudvalidation.student.domain.Student;
import com.bosonit.block7crudvalidation.student.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudiosServiceImpl implements IEstudiosService {
    @Autowired
    IEstudiosRepository estudiosRepository;
    @Autowired
    IStudentRepository studentRepository;
    @Autowired
    IProfesorRepository profesorRepository;

    public EstudiosOutputDTO addEstudios(EstudiosInputDTO estudiosInputDTO) {
        Profesor profesor = profesorRepository.findById(estudiosInputDTO.getIdProfesor()).orElseThrow(
                () -> new EntityNotFoundException("404 - El profesor no existe")
        );

        Estudios estudios = new Estudios(estudiosInputDTO);
        estudios.setProfesor(profesor);
        return estudiosRepository.save(estudios).estudiosToEstudiosOutputDTO();
    }



    public EstudiosOutputDTO getEstudios(int id) {
        return estudiosRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - Los estudios no existen")
        ).estudiosToEstudiosOutputDTO();
    }

    public List<String> getAsignaturasStudent(int id){
        Student student = studentRepository.findById(id).get();
        List<String> lAsignaturas = new ArrayList<>();
        for (Estudios estudios : student.getEstudios()){
            lAsignaturas.add(estudios.getAsignatura());
        }
        return lAsignaturas;
    }

    public List<EstudiosOutputDTO> getAllEstudios() {
        return estudiosRepository.findAll().stream().map(
                estudios -> estudios.estudiosToEstudiosOutputDTO()
        ).toList();
    }

    public EstudiosOutputDTO updateEstudios(int id, EstudiosInputDTO estudiosInputDTO) {
        estudiosRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - Estudios no encontrados"));

        Estudios estudios = new Estudios(estudiosInputDTO);
        estudios.setIdStudy(id);
        return estudiosRepository.save(estudios).estudiosToEstudiosOutputDTO();
    }

    public EstudiosOutputDTO deleteEstudios(int id) {
        Estudios estudios = estudiosRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - Estudios no encontrados")
        );

        if (estudios.getStudent() != null)
            throw new IllegalArgumentException("400 - Los estudios tienen estudiantes asignados");

        estudiosRepository.deleteById(id);
        return estudios.estudiosToEstudiosOutputDTO();
    }
}
