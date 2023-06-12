package com.bosonit.block7crudvalidation.profesor.application;

import com.bosonit.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.persona.repository.IPersonaRepository;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorInputDTO;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorOutputDTO;
import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.profesor.repository.IProfesorRepository;
import com.bosonit.block7crudvalidation.student.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements IProfesorService{
    @Autowired
    IProfesorRepository profesorRepository;
    @Autowired
    IPersonaRepository personaRepository;
    @Autowired
    IStudentRepository studentRepository;

    @Override
    public ProfesorOutputDTO addProfesor (ProfesorInputDTO profesorInputDTO){
        Persona persona = personaRepository.findById(profesorInputDTO.getIdPersona()).orElseThrow(
                () -> new EntityNotFoundException("404 - La persona no existe")
        );

        if (profesorRepository.findByPersona(persona) != null || studentRepository.findByPersona(persona) != null){
            throw new IllegalArgumentException("400 - La persona ya está asignada a otro estudiante/profesor");
        }

        Profesor profesor = new Profesor(profesorInputDTO);
        profesor.setPersona(persona);
        return profesorRepository.save(profesor).profesorToProfesorOutputDTO();
    }

    @Override
    public ProfesorOutputDTO getProfesor (int id){
        return profesorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - El profesor no existe")).profesorToProfesorOutputDTO();
    }

    @Override
    public List<ProfesorOutputDTO> getAllProfesor (){
        return profesorRepository.findAll().stream().map(
                profesor -> profesor.profesorToProfesorOutputDTO()
        ).toList();
    }

    @Override
    public ProfesorOutputDTO updateProfesor (int id, ProfesorInputDTO profesorInputDTO){
        profesorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - El profesor con ese id no existe")
        );

        Profesor profesor = new Profesor(profesorInputDTO);
        profesor.setIdProfesor(id);
        return profesorRepository.save(profesor).profesorToProfesorOutputDTO();
    }

    @Override
    public ProfesorOutputDTO deleteProfesor (int id){
        Profesor profesor = profesorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("404 - El profesor con ese id no existe")
        );

        profesorRepository.deleteById(id);
        return profesor.profesorToProfesorOutputDTO();
    }
}
