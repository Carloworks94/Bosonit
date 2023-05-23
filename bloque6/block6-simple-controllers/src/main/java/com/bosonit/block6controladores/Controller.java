package com.bosonit.block6controladores;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class Controller {

    @GetMapping(value="/user/{nombre}")
    public String saludo(@PathVariable(value="nombre") String name){
        return "Hola " + name;
    }

    @PostMapping(value="/useradd")
    public String formatoJSON (@RequestBody Person persona){
        int edadNueva = persona.getEdad()+1;
        return "La persona tiene " + edadNueva;
    }
}
