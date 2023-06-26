package com.bosonit.block6personcontrollers.controladores;

import com.bosonit.block6personcontrollers.entidades.Ciudad;
import com.bosonit.block6personcontrollers.entidades.Persona;
import com.bosonit.block6personcontrollers.servicio.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/controladorAdd")
public class ControladorAdd {
    @Autowired
    private IServicio iService;

    @Autowired
    @Qualifier("bean1")
    private Persona persona1;

    @Autowired
    @Qualifier("bean2")
    private Persona persona2;

    @Autowired
    @Qualifier("bean3")
    private Persona persona3;

    @GetMapping("/bean/{bean}")
    public ResponseEntity<Persona> beanPersona(@PathVariable(value = "bean") String nombre) {
        if (nombre.equals("bean1"))
            return new ResponseEntity<>(iService.creaPersona(nombre), HttpStatus.CREATED);
        else if (nombre.equals("bean2"))
            return new ResponseEntity<>(iService.creaPersona(nombre), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(iService.creaPersona(nombre), HttpStatus.CREATED);
    }

    @GetMapping("/addPersona")
    public ResponseEntity<Persona> datosPersona(@RequestHeader("name") String nombre,
                                                @RequestHeader("poblacion") String poblacion,
                                                @RequestHeader("edad") int edad) {
        Persona persona = iService.creaPersona(nombre, poblacion, edad);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }

    //en la URL tienen que estar los parametros ?nombre= & nHabitantes=
    @PostMapping("/addCiudad")
    public ResponseEntity<Ciudad> nuevaCiudad(@RequestParam String nombre, @RequestParam(name = "nHabitantes") int numeroHabitantes) {
        Ciudad ciudad = new Ciudad(nombre, numeroHabitantes);
        iService.addCiudad(ciudad);
        return new ResponseEntity<>(ciudad, HttpStatus.CREATED);
    }
}
