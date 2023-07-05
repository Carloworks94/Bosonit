package com.bosonit.block12mongodb.domain;

import com.bosonit.block12mongodb.controller.dto.PersonaInputDTO;
import com.bosonit.block12mongodb.controller.dto.PersonaOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Persona")
public class Persona {
    @Id
    String id;

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
