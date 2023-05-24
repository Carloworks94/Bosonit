package com.bosonit.block6personcontrollers.controladores;

import com.bosonit.block6personcontrollers.entidades.Persona;
import com.bosonit.block6personcontrollers.servicio.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/controladorAdd")
public class ControladorAdd {
    @Autowired
    private IServicio Iservice;


    @GetMapping("/addPersona")
    public ResponseEntity<Persona> datosPersona(@RequestHeader("name") String nombre,
                                                @RequestHeader("poblacion") String poblacion,
                                                @RequestHeader("edad") int edad){
        Persona persona = Iservice.creaPersona(nombre,poblacion,edad);
        return new ResponseEntity<>(persona, HttpStatus.CREATED );
    }
}
