package com.bosonit.block6personcontrollers.servicio;

import com.bosonit.block6personcontrollers.entidades.Ciudad;
import com.bosonit.block6personcontrollers.entidades.Persona;

import java.util.List;

public interface IServicio {
    public Persona creaPersona(String nombre, String poblacion, int edad);

    public Persona creaPersona(String nombre);

    public Persona modificaEdad(Persona persona);

    public Persona getPersona();

    public List<Ciudad> getlCiudades();

    public void addCiudad(Ciudad ciudad);
}
