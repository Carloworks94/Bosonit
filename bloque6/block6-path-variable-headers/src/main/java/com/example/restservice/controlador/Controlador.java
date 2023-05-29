package com.example.restservice.controlador;

import com.example.restservice.entidades.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class Controlador {

    @PostMapping("/objetoJSON")
    public Person objetoJSON(@RequestBody Person persona) {
        return persona;
    }

    //no tiene mucho sentido el optional en este caso ya que por la url no podemos pasar un valor vacío con @PathVariable
    //sin embargo, si antes del {id} ponemos cualquier caracter, el id sí puede tomar como valor cadena vacía
    @GetMapping("/user/{id}")
    public String id(@PathVariable(value = "id") Optional<String> id) {
        return id.isPresent() ? "id:" + id.get() : "id: NULO";
    }

    @PutMapping("/post")
    public HashMap<String, Integer> mapaID(@RequestParam(name = "var1") Integer variable1, @RequestParam Integer var2) {
        HashMap<String, Integer> mapa = new HashMap<>();
        mapa.put("var1", variable1);
        mapa.put("var2", var2);
        return mapa;
    }
}
