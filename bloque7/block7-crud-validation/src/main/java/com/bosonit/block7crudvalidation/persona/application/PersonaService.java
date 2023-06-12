package com.bosonit.block7crudvalidation.persona.application;


import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.persona.domain.Persona;

import java.util.List;

public interface PersonaService {
    //CREATE
    PersonaOutputDTO addPersona (PersonaInputDTO personaInputDTO) throws Exception;

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
