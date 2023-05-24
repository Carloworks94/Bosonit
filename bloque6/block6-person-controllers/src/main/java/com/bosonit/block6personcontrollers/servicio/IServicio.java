package com.bosonit.block6personcontrollers.servicio;

import com.bosonit.block6personcontrollers.entidades.Persona;

public interface IServicio{
    public Persona creaPersona(String nombre, String poblacion, int edad);

    public Persona modificaEdad (Persona persona);

    public Persona getPersona ();
}
