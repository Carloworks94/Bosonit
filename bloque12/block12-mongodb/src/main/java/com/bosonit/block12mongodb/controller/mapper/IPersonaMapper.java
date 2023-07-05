package com.bosonit.block12mongodb.controller.mapper;

import com.bosonit.block12mongodb.controller.dto.PersonaOutputDTO;
import com.bosonit.block12mongodb.domain.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface IPersonaMapper {
    //Si hay algún campo que no coincida, hay que meterlo con @Mapping
    @Mapping(target = "user", source = "userPersona")
    PersonaOutputDTO personaToPersonaOutputDTO(Persona persona);

}
