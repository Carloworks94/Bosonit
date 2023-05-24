package com.bosonit.block6personcontrollers.servicio;

import com.bosonit.block6personcontrollers.entidades.Persona;
import org.springframework.stereotype.Service;

@Service
public class Servicio implements IServicio{
    private Persona persona;
    public Persona creaPersona(String nombre, String poblacion, int edad){
        persona = new Persona(nombre, poblacion, edad);
        return persona;
    }

    public Persona modificaEdad (Persona persona){
        persona.setEdad(persona.getEdad()*2);
        return persona;
    }

    public Persona getPersona (){
        return persona;
    }

}
