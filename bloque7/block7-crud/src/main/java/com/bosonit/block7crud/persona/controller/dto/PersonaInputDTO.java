package com.bosonit.block7crud.persona.controller.dto;

import com.bosonit.block7crud.persona.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaInputDTO {
    int id;
    String name;
    String edad;
    String poblacion;

    public Persona personaInputDTOtoPersona(Persona persona) {
        if (this.name != null) {
            persona.setNombre(this.name);
        }
        if (this.edad != null) {
            persona.setEdad(this.edad);
        }
        if (this.poblacion != null) {
            persona.setPoblacion(this.poblacion);
        }
        return persona;
    }
}
