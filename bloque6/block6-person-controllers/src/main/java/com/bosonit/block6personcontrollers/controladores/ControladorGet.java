package com.bosonit.block6personcontrollers.controladores;

import com.bosonit.block6personcontrollers.entidades.Persona;
import com.bosonit.block6personcontrollers.servicio.IServicio;
import com.bosonit.block6personcontrollers.servicio.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/controladorGet")
public class ControladorGet {
    @Autowired
    private IServicio Iservice;

    @GetMapping("/getPersona")
    public Persona getPersona (){
        return Iservice.modificaEdad(Iservice.getPersona());
    }
}
