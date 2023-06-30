package com.bosonit.block7crudvalidation.persona.controller;


import com.bosonit.block7crudvalidation.persona.application.PersonaServiceImpl;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDTO;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.persona.controller.mapper.IPersonaMapper;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorOutputDTO;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/persona")
@Validated
public class PersonaController {
    //a su vez el servicio hace un autowired del repositorio JPA
    @Autowired
    PersonaServiceImpl personaService;
//    @Autowired
//    PersonaFeign personaFeign;

    IPersonaMapper mapper = Mappers.getMapper(IPersonaMapper.class);

    @PostMapping
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody @Valid PersonaInputDTO persona) throws Exception {
        URI location = URI.create("/persona");
        //return new ResponseEntity<>(personaService.addPersona(persona), HttpStatus.CREATED);
        //se puede hacer el return de ambas maneras
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.addPersona(persona));

        //tambien se puede agregar un location para indicar la direccion donde se ha creado el objeto Persona
        //return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }

    @GetMapping("/profesor/{id}")
    public ResponseEntity<ProfesorOutputDTO> getProfesor(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(personaService.getProfesor(id).orElseThrow());
    }


//    @GetMapping("/profesor/{id}")
//    public ResponseEntity<ProfesorOutputDTO> getProfesor(@PathVariable int id){
//        ProfesorOutputDTO profesor = personaFeign.getProfesor(id);
//        return ResponseEntity.status(HttpStatus.OK).body(profesor);
//    }

    @GetMapping("{id}")
    public ResponseEntity<PersonaOutputDTO> getPersona(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(personaService.getPersona(id));

    }


    @GetMapping
    public ResponseEntity<List<PersonaOutputDTO>> getAllPersonas() {
        return new ResponseEntity<>(personaService.getAllPersonas(), HttpStatus.OK);
    }

    //Método que devuelve la lista con todas las personas que coinciden con el nombre pasado como parámetro
    @GetMapping("/user/{user}")
    public ResponseEntity<List<PersonaOutputDTO>> getPersona(@PathVariable String user) { //FIXME: pasar personas a OutputDTO --> Una forma sería con el mapper, y otra directamente utilizando la conversion de flujo de datos .stream()
//        List<PersonaOutputDTO> listaPO = new ArrayList<>();
//        List<Persona> listaP = personaService.getPersonas(user);
//        for (Persona p : listaP){
//            listaPO.add(mapper.personaToPersonaOutputDTO(p));  //FIXME: pasar este for a un metodo del mapper que te devuelva una lista de PersonaOutputDTO
//        }
        //Esto hace lo mismo que con el mapper comentado arriba
        List<PersonaOutputDTO> listaP1 = personaService.getPersonas(user).stream().map(persona -> persona.personaToPersonaOutputDTO()).toList();
        return new ResponseEntity<>(listaP1, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<Iterable<PersonaOutputDTO>> getPersonasCustomQuery(@RequestParam(required = false) String userPersona,
                                                                             @RequestParam(required = false) String name,
                                                                             @RequestParam(required = false) String surname,
                                                                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdDate,
                                                                             @RequestParam(required = false) String fechaOrder,
                                                                             @RequestParam(required = false) String order,
                                                                             @RequestParam int pageNumber,
                                                                             @RequestParam(defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok().body(personaService.getPersonasCustomQuery(userPersona, name, surname, createdDate, fechaOrder, order, pageNumber, pageSize));
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updatePersona(@PathVariable int id, @RequestBody PersonaInputDTO personaInputDTO) {
        try {
            //comprobamos que la persona esté creada en el metodo update del servicio, si no lanza excepcion
            return ResponseEntity.ok().body(personaService.updatePersona(id, personaInputDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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
