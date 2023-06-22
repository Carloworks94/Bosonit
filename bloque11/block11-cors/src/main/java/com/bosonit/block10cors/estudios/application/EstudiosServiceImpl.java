package com.bosonit.block10cors.estudios.application;

import com.bosonit.block10cors.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block10cors.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block10cors.estudios.controller.dto.EstudiosSimpleOutputDTO;
import com.bosonit.block10cors.estudios.domain.Estudios;
import com.bosonit.block10cors.estudios.repository.IEstudiosRepository;
import com.bosonit.block10cors.exceptions.EntityNotFoundException;
import com.bosonit.block10cors.exceptions.IllegalArgumentException;
import com.bosonit.block10cors.profesor.domain.Profesor;
import com.bosonit.block10cors.profesor.repository.IProfesorRepository;
import com.bosonit.block10cors.student.domain.Student;
import com.bosonit.block10cors.student.repository.IStudentRepository;
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

    @Override
    public EstudiosOutputDTO addEstudios(EstudiosInputDTO estudiosInputDTO) {
        Profesor profesor = profesorRepository.findById(estudiosInputDTO.getIdProfesor()).orElseThrow(
                () -> new EntityNotFoundException("404 - El profesor no existe")
        );

        Estudios estudios = new Estudios(estudiosInputDTO);
        estudios.setProfesor(profesor);
        return estudiosRepository.save(estudios).estudiosToEstudiosOutputDTO();
    }


    @Override
    public EstudiosOutputDTO getEstudios(int id) {
        Estudios estudios = estudiosRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - Los estudios no existen")
        );
        return estudios.estudiosToEstudiosOutputDTO();
    }

    @Override
    public EstudiosSimpleOutputDTO getSimpleEstudios(int id) {
        Estudios estudios = estudiosRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - Los estudios no existen")
        );
        return estudios.estudiosToEstudiosSimpleOutputDTO();
    }

    @Override
    public List<String> getAsignaturasStudent(int id){
        Student student = studentRepository.findById(id).get();
        List<String> lAsignaturas = new ArrayList<>();
        for (Estudios estudios : student.getEstudios()){
            lAsignaturas.add(estudios.getAsignatura());
        }
        return lAsignaturas;
    }

    @Override
    public List<EstudiosOutputDTO> getAllEstudios() {
        return estudiosRepository.findAll().stream().map(
                estudios -> estudios.estudiosToEstudiosOutputDTO()
        ).toList();
    }

    @Override
    public EstudiosOutputDTO updateEstudios(int id, EstudiosInputDTO estudiosInputDTO) {
        estudiosRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - Estudios no encontrados"));
        Profesor profesor = profesorRepository.findById(estudiosInputDTO.getIdProfesor()).orElseThrow(
                () -> new EntityNotFoundException("404 - El profesor no existe")
        );

        Estudios estudios = new Estudios(estudiosInputDTO);
        estudios.setIdStudy(id);
        estudios.setProfesor(profesor);
        return estudiosRepository.save(estudios).estudiosToEstudiosOutputDTO();
    }

    @Override
    public EstudiosOutputDTO deleteEstudios(int id) {
        Estudios estudios = estudiosRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - Estudios no encontrados")
        );

        if (estudios.getStudents() != null)
            throw new IllegalArgumentException("400 - Los estudios tienen estudiantes asignados");

        estudiosRepository.deleteById(id);
        return estudios.estudiosToEstudiosOutputDTO();
    }
}
