package com.bosonit.block7crud.persona.controller.mapper;

import com.bosonit.block7crud.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crud.persona.domain.Persona;
import org.mapstruct.Mapper;


@Mapper
public interface IPersonaMapper {
    //con la version 1.5.2.Final en la dependencia de mapperStruct tienes que añadir los target de los atributos con la anotacion
    //@Mapping(target="nombre", source="nombre")
    PersonaOutputDTO personaToPersonaOutputDTO(Persona persona);

}
