package com.bosonit.block10cors.persona.controller.dto;

import com.bosonit.block10cors.persona.domain.Persona;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PersonaInputDTO {
    String usuario;
    String password;
    String name;
    String surname;
    //@Column(unique = true) FIXME: hacerlo unico pero en el Input, para que no se autogenere el ID de un objeto Persona fallido.
    @Email(message = "El CompanyEmail no tiene formato correcto")
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;

    public PersonaInputDTO(int id, String usuario, String password, String name, String surname, String company_email,
                           String personal_email, String city, Boolean active, Date created_date, String imagen_url, Date termination_date) {
        this.usuario = usuario;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.company_email = company_email;
        this.personal_email = personal_email;
        this.city = city;
        this.active = active;
        this.created_date = created_date;
        this.imagen_url = imagen_url;
        this.termination_date = termination_date;
    }

    public Persona personaInputDTOtoPersona(Persona persona) {
        if (this.usuario != null) {
            persona.setUserPersona(this.usuario);
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
        if (this.company_email != null) {
            persona.setCompanyEmail(this.company_email);
        }
        if (this.personal_email != null) {
            persona.setPersonalEmail(this.personal_email);
        }
        if (this.company_email != null) {
            persona.setCompanyEmail(this.company_email);
        }
        if (this.city != null) {
            persona.setCity(this.city);
        }
        if (this.active != null) {
            persona.setActive(this.active);
        }
        if (this.created_date != null) {
            persona.setCreatedDate(this.created_date);
        }
        if (this.imagen_url != null) {
            persona.setImagenUrl(this.imagen_url);
        }
        if (this.termination_date != null) {
            persona.setTerminationDate(this.termination_date);
        }

        return persona;
    }
}
