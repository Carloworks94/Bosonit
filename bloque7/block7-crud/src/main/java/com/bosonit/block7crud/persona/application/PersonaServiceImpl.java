package com.bosonit.block7crud.persona.application;


import com.bosonit.block7crud.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crud.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crud.persona.domain.Persona;
import com.bosonit.block7crud.persona.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) {
        return personaRepository.save(new Persona(personaInputDTO)).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDTO getPersona(int id) throws Exception {
        return personaRepository.findById(id).orElseThrow(() -> new Exception("404 - Persona no encontrada")).personaToPersonaOutputDTO();
    }

    @Override
    public List<Persona> getPersonas(String nombre) { //FIXME: cambiar a personaOutputDTO
        //aqui podriamos utilizar por ejemplo stream() para transformar el flujo de datos objetos tipo PersonaOutputDTO y así no usar un mapper en el controlador. Ej:
        //personaRepository.findAllByNombre(nombre).stream().map(persona -> persona.personaToPersonaOutputDTO()).toList();
        return personaRepository.findAllByNombre(nombre);
    }

/*    @Override
    public Iterable<PersonaOutputDTO> getPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize); //si ponemos por ejemplo la pag 0, y el numero max de elementos de la base de datos, nos mostraría todos los elementos
//        List<PersonaOutputDTO> list;
//        List<Persona> content = personaRepository.findAll(pageRequest).getContent();
//        List<PersonaOutputDTO> result = new ArrayList<>();
//        for (Persona persona : content) {
//            PersonaOutputDTO personaToPersonaOutputDTO = persona.personaToPersonaOutputDTO();
//            result.add(personaToPersonaOutputDTO);
//        }
//        list = result;
//        return list;
        //este codigo es lo mismo que lo comentado arriba
        Iterable<PersonaOutputDTO> iterador = personaRepository.findAll(pageRequest)
                .stream()
                .map(persona -> persona.personaToPersonaOutputDTO())
                .toList();
        return iterador;
    }*/

    @Override
    public List<PersonaOutputDTO> getAllPersonas(){
        return personaRepository.findAll().stream().map(persona -> persona.personaToPersonaOutputDTO()).toList();
    }

    @Override
    public PersonaOutputDTO updatePersona(PersonaInputDTO personaInputDTO) throws Exception {
        Persona persona = personaRepository.findById(personaInputDTO.getId()).orElseThrow(() -> new Exception("404 - Persona no encontrada"));
        persona = personaInputDTO.personaInputDTOtoPersona(persona);
        return personaRepository.save(persona).personaToPersonaOutputDTO();

    }

    @Override
    public PersonaOutputDTO updatePersona(int id) {
        personaRepository.findById(id).orElseThrow();
        return personaRepository.save(personaRepository.findById(id).get())
                .personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDTO deletePersona(int id) throws Exception {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new Exception("404 - Persona no encontrada"));
        personaRepository.deleteById(id);
        return persona.personaToPersonaOutputDTO();
    }
}
