package com.bosonit.block12mongodb.application;

import com.bosonit.block12mongodb.controller.dto.PersonaInputDTO;
import com.bosonit.block12mongodb.controller.dto.PersonaOutputDTO;
import com.bosonit.block12mongodb.domain.Persona;
import com.bosonit.block12mongodb.exceptions.EntityNotFoundException;
import com.bosonit.block12mongodb.exceptions.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception {
        if (personaInputDTO.getUser() == null)
            throw new UnprocessableEntityException("Usuario no puede ser nulo");
        if (personaInputDTO.getUser().length() > 10)
            throw new UnprocessableEntityException("Longitud de usuario no puede ser mayor a 10 caracteres");
        if (personaInputDTO.getUser().length() < 6)
            throw new UnprocessableEntityException("Longitud de usuario no puede ser menor a 6 caracteres");
        if (personaInputDTO.getPassword() == null)
            throw new UnprocessableEntityException("Password no puede ser nulo");
        if (personaInputDTO.getName() == null)
            throw new UnprocessableEntityException("Nombre no puede ser nulo");
        if (personaInputDTO.getCompanyEmail() == null)
            throw new UnprocessableEntityException("El email de la empresa no puede ser nulo");
        if (personaInputDTO.getPersonalEmail() == null)
            throw new UnprocessableEntityException("El email personal no puede ser nulo");
        if (personaInputDTO.getCity() == null)
            throw new UnprocessableEntityException("La ciudad no puede ser nulo");
        if (personaInputDTO.getActive() == null)
            throw new UnprocessableEntityException("La actividad no puede ser nulo");
        if (personaInputDTO.getCreatedDate() == null)
            throw new UnprocessableEntityException("La fecha de creacion no puede ser nulo");

        Query query = new Query(Criteria.where("companyEmail").is(personaInputDTO.getCompanyEmail()));
        if (mongoTemplate.exists(query,Persona.class))
            throw new UnprocessableEntityException("Email repetido");

        Persona persona = new Persona(personaInputDTO);
        return mongoTemplate.save(persona).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDTO getPersona(String id){
        Persona persona = Optional.ofNullable(mongoTemplate.findById(id, Persona.class))
                .orElseThrow(() -> new EntityNotFoundException("404 - Persona no encontrada"));
        return persona.personaToPersonaOutputDTO();
    }

    //Metodo que encuentra todas las personas con ese mismo usuario
    @Override
    public List<Persona> getPersonas(String user) {
        Query query = new Query (Criteria.where("user").is(user));
        return mongoTemplate.find(query,Persona.class);
    }



/*    @Override
    public Iterable<PersonaOutputDTO> getPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize); //si ponemos por ejemplo la pag 0, y el numero max de elementos de la base de datos, nos mostraría todos los elementos
//        List<PersonaOutputDTO> list;
//        List<Persona> content = IEstudiosRepository.findAll(pageRequest).getContent();
//        List<PersonaOutputDTO> result = new ArrayList<>();
//        for (Persona persona : content) {
//            PersonaOutputDTO personaToPersonaOutputDTO = persona.personaToPersonaOutputDTO();
//            result.add(personaToPersonaOutputDTO);
//        }
//        list = result;
//        return list;
        //este codigo es lo mismo que lo comentado arriba
        Iterable<PersonaOutputDTO> iterador = IEstudiosRepository.findAll(pageRequest)
                .stream()
                .map(persona -> persona.personaToPersonaOutputDTO())
                .toList();
        return iterador;
    }*/

    @Override
    public List<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize) {
        Query query = new Query();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return mongoTemplate.find(query.with(pageRequest), Persona.class)
                .stream().map(persona -> persona.personaToPersonaOutputDTO()).toList();
    }

    @Override //FIXME: problema de inconsistencia, aquí no se comprueban las validaciones
    public PersonaOutputDTO updatePersona(String id, PersonaInputDTO personaInputDTO) throws Exception {
        Persona persona = Optional.ofNullable(mongoTemplate.findById(id, Persona.class))
                .orElseThrow(() -> new EntityNotFoundException("404 - Persona no encontrada"));

        persona = personaInputDTO.personaInputDTOtoPersona(persona);
        return mongoTemplate.save(persona).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDTO deletePersona(String id) throws Exception {
        Persona persona = Optional.ofNullable(mongoTemplate.findById(id, Persona.class))
                .orElseThrow(() -> new EntityNotFoundException("404 - Persona no encontrada"));
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Persona.class);
        return persona.personaToPersonaOutputDTO();
    }

}
