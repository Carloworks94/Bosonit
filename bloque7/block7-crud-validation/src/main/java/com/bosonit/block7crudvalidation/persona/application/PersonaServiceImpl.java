package com.bosonit.block7crudvalidation.persona.application;


import com.bosonit.block7crudvalidation.exceptions.IllegalArgumentException;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.bosonit.block7crudvalidation.persona.repository.IPersonaRepository;
import com.bosonit.block7crudvalidation.profesor.repository.IProfesorRepository;
import com.bosonit.block7crudvalidation.student.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    IPersonaRepository IPersonaRepository;
    @Autowired
    IProfesorRepository profesorRepository;
    @Autowired
    IStudentRepository studentRepository;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception {
        if (personaInputDTO.getUser() == null)
            throw new UnprocessableEntityException("Usuario no puede ser nulo");
        if (personaInputDTO.getUser().length()>10)
            throw new UnprocessableEntityException("Longitud de usuario no puede ser mayor a 10 caracteres");
        if (personaInputDTO.getUser().length()<6)
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

        return IPersonaRepository.save(new Persona(personaInputDTO)).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDTO getPersona(int id) throws Exception {
        return IPersonaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona no encontrada")).personaToPersonaOutputDTO();
    }

    @Override
    public List<Persona> getPersonas(String user) {
        //aqui podriamos utilizar por ejemplo stream() para transformar el flujo de datos objetos tipo PersonaOutputDTO y así no usar un mapper en el controlador. Ej:
        //IEstudiosRepository.findAllByNombre(nombre).stream().map(persona -> persona.personaToPersonaOutputDTO()).toList();
        return IPersonaRepository.findAllByUserPersona(user);
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
    public List<PersonaOutputDTO> getAllPersonas(){
        return IPersonaRepository.findAll().stream().map(persona -> persona.personaToPersonaOutputDTO()).toList();
    }

    @Override //FIXME: aquí no se comprueban las validaciones
    public PersonaOutputDTO updatePersona(int id, PersonaInputDTO personaInputDTO) throws Exception {
        Persona persona = IPersonaRepository.findById(id).orElseThrow(() -> new Exception("404 - Persona no encontrada"));

        persona = personaInputDTO.personaInputDTOtoPersona(persona);
        return IPersonaRepository.save(persona).personaToPersonaOutputDTO();
    }


    @Override
    public PersonaOutputDTO deletePersona(int id) throws Exception {
        Persona persona = IPersonaRepository.findById(id).orElseThrow(() -> new Exception("404 - Persona no encontrada"));
        if (profesorRepository.findByPersona(persona) != null || studentRepository.findByPersona(persona) != null)
            throw new IllegalArgumentException("400 - La persona es un estudiante o profesor");
        IPersonaRepository.deleteById(id);
        return persona.personaToPersonaOutputDTO();
    }
}
