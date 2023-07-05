package com.bosonit.block12mongodb.application;

import com.bosonit.block12mongodb.controller.dto.PersonaInputDTO;
import com.bosonit.block12mongodb.controller.dto.PersonaOutputDTO;
import com.bosonit.block12mongodb.domain.Persona;

import java.util.List;

public interface IPersonaService {
    //CREATE
    PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;

    //READ
    PersonaOutputDTO getPersona(String id) throws Exception;

    List<Persona> getPersonas(String nombre);

    //Iterable<PersonaOutputDTO> getPersonas(int pageNumber, int pageSize);
    List<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);

    //UPDATE
    PersonaOutputDTO updatePersona(String id, PersonaInputDTO personaInputDTO) throws Exception;

    //DELETE
    PersonaOutputDTO deletePersona(String id) throws Exception;

}
