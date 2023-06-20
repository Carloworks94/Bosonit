package com.bosonit.block7crudvalidation.persona.application;


import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorOutputDTO;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    //CREATE
    PersonaOutputDTO addPersona (PersonaInputDTO personaInputDTO) throws Exception;
    Optional<ProfesorOutputDTO> getProfesor(String id);
    //READ
    PersonaOutputDTO getPersona (int id) throws Exception;
    List<Persona> getPersonas(String nombre);
    //Iterable<PersonaOutputDTO> getPersonas(int pageNumber, int pageSize);
    List<PersonaOutputDTO> getAllPersonas();

    //UPDATE
    PersonaOutputDTO updatePersona (int id, PersonaInputDTO personaInputDTO) throws Exception;

    //DELETE
    PersonaOutputDTO deletePersona (int id) throws Exception;

}
