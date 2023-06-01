package com.bosonit.block7crud.persona.response;


import com.bosonit.block7crud.persona.controller.dto.PersonaOutputDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaPersona {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonaOutputDTO personaOutputDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String respuesta;
}
