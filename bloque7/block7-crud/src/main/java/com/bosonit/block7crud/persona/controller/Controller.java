package com.bosonit.block7crud.persona.controller;


import com.bosonit.block7crud.persona.application.PersonaServiceImpl;
import com.bosonit.block7crud.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crud.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crud.persona.controller.mapper.IPersonaMapper;
import com.bosonit.block7crud.persona.domain.Persona;
import com.bosonit.block7crud.persona.response.RespuestaPersona;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class Controller {
    //a su vez el servicio hace un autowired del repositorio JPA
    @Autowired
    PersonaServiceImpl personaService;

    IPersonaMapper mapper = Mappers.getMapper(IPersonaMapper.class);

    @PostMapping
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody PersonaInputDTO persona) {
        URI location = URI.create("/persona");
        //return new ResponseEntity<>(personaService.addPersona(persona), HttpStatus.CREATED);
        //se puede hacer el return de ambas maneras
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.addPersona(persona));
        //tambien se puede agregar un location para indicar la direccion donde se ha creado el objeto Persona
        //return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonaOutputDTO> getPersona(@PathVariable int id) {
        try {
            return ResponseEntity.ok(personaService.getPersona(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonaOutputDTO>> getAllPersonas() {
        return new ResponseEntity<>(personaService.getAllPersonas(), HttpStatus.OK);
    }

    //Método que devuelve la lista con todas las personas que coinciden con el nombre pasado como parámetro
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonaOutputDTO>> getPersona(@PathVariable String nombre) { //FIXME: pasar personas a OutputDTO --> Una forma sería con el mapper, y otra directamente utilizando la conversion de flujo de datos .stream()
        List<Persona> listaP = new ArrayList<>();
        List<PersonaOutputDTO> listaPO = new ArrayList<>();
        listaP = personaService.getPersonas(nombre);
        for (Persona p : listaP) {
            listaPO.add(mapper.personaToPersonaOutputDTO(p));  //FIXME: pasar este for a un metodo del mapper que te devuelva una lista de PersonaOutputDTO
        }
        return new ResponseEntity<>(listaPO, HttpStatus.OK);
    }

//    @GetMapping("/pages")
//    public Iterable<PersonaOutputDTO> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
//                                                     @RequestParam(defaultValue = "4", required = false) int pageSize){
//        return personaService.getPersonas(pageNumber,pageSize);
//    }


    @PutMapping()
    public ResponseEntity<?> updatePersona(@RequestBody PersonaInputDTO personaInputDTO) {
        try {
            //comprobamos que la persona esté creada en el metodo update del servicio, si no lanza excepcion
            return ResponseEntity.ok().body(personaService.updatePersona(personaInputDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Otra opcion para mostrar un mensaje junto a la persona es crear un objeto RespuestaPersona (PersonaOutputDTO, mensaje)
    @PutMapping("/respuesta")
    public ResponseEntity<RespuestaPersona> updatePersonaRespuesta(@RequestBody PersonaInputDTO personaInputDTO) {
        RespuestaPersona respuesta = new RespuestaPersona();
        try {
            //comprobamos que la persona esté creada en el metodo update del servicio, si no lanza excepcion
            respuesta.setPersonaOutputDTO(personaService.updatePersona(personaInputDTO));
            respuesta.setRespuesta("200 - Persona actualizada correctamente");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            respuesta.setRespuesta(e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePersona(@PathVariable int id) { //TODO: no muestra la persona borrada por el body
        try {
            //si en vez de devolver el codigo OK volvemos un 204 (NO CONTENT) no devuelve el objeto persona
            return ResponseEntity.status(HttpStatus.OK).body(personaService.deletePersona(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
