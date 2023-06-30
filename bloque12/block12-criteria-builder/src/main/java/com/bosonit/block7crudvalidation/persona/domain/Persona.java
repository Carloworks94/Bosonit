package com.bosonit.block7crudvalidation.persona.domain;

import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String userPersona;

    String password;

    String name;

    String surname;

    String companyEmail;

    String personalEmail;

    String city;

    Boolean active;

    Date createdDate;

    String imagenUrl;

    Date terminationDate;


    public Persona(PersonaInputDTO personaInputDTO) {
        this.userPersona = personaInputDTO.getUser();
        this.password = personaInputDTO.getPassword();
        this.name = personaInputDTO.getName();
        this.surname = personaInputDTO.getSurname();
        this.companyEmail = personaInputDTO.getCompanyEmail();
        this.personalEmail = personaInputDTO.getPersonalEmail();
        this.city = personaInputDTO.getCity();
        this.active = personaInputDTO.getActive();
        this.createdDate = personaInputDTO.getCreatedDate();
        this.imagenUrl = personaInputDTO.getImagenUrl();
        this.terminationDate = personaInputDTO.getTerminationDate();
    }

    public PersonaOutputDTO personaToPersonaOutputDTO() {
        return new PersonaOutputDTO(this.id, this.userPersona, this.name, this.surname, this.companyEmail,
                this.personalEmail, this.city, this.active, this.createdDate, this.imagenUrl, this.terminationDate);
    }
}
