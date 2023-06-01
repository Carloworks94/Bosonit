package com.bosonit.block7crud.persona.domain;

;
import com.bosonit.block7crud.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crud.persona.controller.dto.PersonaOutputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    int id;

    String nombre;

    String edad;

    String poblacion;

    public Persona (PersonaInputDTO personaInputDTO){
        this.id = personaInputDTO.getId();
        this.nombre = personaInputDTO.getName();
        this.edad = personaInputDTO.getEdad();
        this.poblacion = personaInputDTO.getPoblacion();
    }

    public PersonaOutputDTO personaToPersonaOutputDTO(){
        return new PersonaOutputDTO(this.id, this.nombre, this.edad, this.poblacion);
    }
}
