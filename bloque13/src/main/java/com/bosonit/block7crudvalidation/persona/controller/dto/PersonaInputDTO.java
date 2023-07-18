package com.bosonit.block7crudvalidation.persona.controller.dto;

import com.bosonit.block7crudvalidation.persona.domain.Persona;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PersonaInputDTO {
    String user;
    String password;
    String name;
    String surname;
    //@Column(unique = true) FIXME: hacerlo unico pero en el Input, para que no se autogenere el ID de un objeto Persona fallido.
    @Email(message = "El CompanyEmail no tiene formato correcto")
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    Date createdDate;
    String imagenUrl;
    Date terminationDate;

    public PersonaInputDTO(String user, String password, String name, String surname, String companyEmail,
                           String personalEmail, String city, Boolean active, Date createdDate, String imagenUrl, Date terminationDate) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.companyEmail = companyEmail;
        this.personalEmail = personalEmail;
        this.city = city;
        this.active = active;
        this.createdDate = createdDate;
        this.imagenUrl = imagenUrl;
        this.terminationDate = terminationDate;
    }

    public Persona personaInputDTOtoPersona(Persona persona) {
        if (this.user != null) {
            persona.setUserPersona(this.user);
        }
        if (this.name != null) {
            persona.setPassword(this.password);
        }
        if (this.name != null) {
            persona.setName(this.name);
        }
        if (this.surname != null) {
            persona.setSurname(this.surname);
        }
        if (this.companyEmail != null) {
            persona.setCompanyEmail(this.companyEmail);
        }
        if (this.personalEmail != null) {
            persona.setPersonalEmail(this.personalEmail);
        }
        if (this.companyEmail != null) {
            persona.setCompanyEmail(this.companyEmail);
        }
        if (this.city != null) {
            persona.setCity(this.city);
        }
        if (this.active != null) {
            persona.setActive(this.active);
        }
        if (this.createdDate != null) {
            persona.setCreatedDate(this.createdDate);
        }
        if (this.imagenUrl != null) {
            persona.setImagenUrl(this.imagenUrl);
        }
        if (this.terminationDate != null) {
            persona.setTerminationDate(this.terminationDate);
        }

        return persona;
    }
}
