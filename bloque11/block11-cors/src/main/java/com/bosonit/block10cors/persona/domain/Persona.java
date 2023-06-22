package com.bosonit.block10cors.persona.domain;

import com.bosonit.block10cors.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block10cors.persona.controller.dto.PersonaOutputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

;import java.util.Date;

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


    public Persona (PersonaInputDTO personaInputDTO){
        this.userPersona = personaInputDTO.getUsuario();
        this.password = personaInputDTO.getPassword();
        this.name = personaInputDTO.getName();
        this.surname = personaInputDTO.getSurname();
        this.companyEmail = personaInputDTO.getCompany_email();
        this.personalEmail = personaInputDTO.getPersonal_email();
        this.city = personaInputDTO.getCity();
        this.active = personaInputDTO.getActive();
        this.createdDate = personaInputDTO.getCreated_date();
        this.imagenUrl = personaInputDTO.getImagen_url();
        this.terminationDate = personaInputDTO.getTermination_date();
    }

    public PersonaOutputDTO personaToPersonaOutputDTO(){
        return new PersonaOutputDTO(this.id, this.userPersona, this.name, this.surname, this.companyEmail,
        this.personalEmail, this.city, this.active, this.createdDate, this.imagenUrl, this.terminationDate);
    }
}
