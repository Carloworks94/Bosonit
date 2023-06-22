package com.bosonit.block10cors.persona.controller.mapper;

import com.bosonit.block10cors.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block10cors.persona.domain.Persona;
import org.mapstruct.Mapper;


@Mapper
public interface IPersonaMapper {
    //Si hay algún campo que no coincida, hay que meterlo con @Mapping
    //@Mapping(target="user", source="userPersona")
    PersonaOutputDTO personaToPersonaOutputDTO (Persona persona);

}
