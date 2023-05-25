package com.bosonit.block6personcontrollers.controladores;

import com.bosonit.block6personcontrollers.entidades.Ciudad;
import com.bosonit.block6personcontrollers.entidades.Persona;
import com.bosonit.block6personcontrollers.servicio.IServicio;
import com.bosonit.block6personcontrollers.servicio.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/controladorGet")
public class ControladorGet {
    @Autowired
    private IServicio iService;

    @GetMapping("/getPersona")
    public Persona getPersona (){
        return iService.modificaEdad(iService.getPersona());
    }

    @GetMapping("/getCiudades")
    public ResponseEntity<List<Ciudad>> getCiudades(){
        return new ResponseEntity<>(iService.getlCiudades(), HttpStatus.OK);
    }

}
