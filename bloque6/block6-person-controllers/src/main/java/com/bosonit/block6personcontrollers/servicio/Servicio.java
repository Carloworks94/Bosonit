package com.bosonit.block6personcontrollers.servicio;

import com.bosonit.block6personcontrollers.entidades.Ciudad;
import com.bosonit.block6personcontrollers.entidades.Persona;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Servicio implements IServicio {
    private Persona persona;
    private List<Ciudad> lCiudades;

    @PostConstruct
    public void listaCiudades() {
        lCiudades = new ArrayList<>();
    }

    @Bean
    public Persona bean1() {
        return new Persona("bean1");
    }

    @Bean
    public Persona bean2() {
        return new Persona("bean2");
    }

    @Bean
    public Persona bean3() {
        return new Persona("bean3");
    }


    public Persona creaPersona(String nombre, String poblacion, int edad) {
        persona = new Persona(nombre, poblacion, edad);
        return persona;
    }

    public Persona creaPersona(String nombre) {
        persona = new Persona(nombre);
        return persona;
    }

    public Persona modificaEdad(Persona persona) {
        persona.setEdad(persona.getEdad() * 2);
        return persona;
    }

    public void addCiudad(Ciudad ciudad) {
        lCiudades.add(ciudad);
    }

    public Persona getPersona() {
        return persona;
    }

    public List<Ciudad> getlCiudades() {
        return lCiudades;
    }


}
