package com.bosonit.block12mongodb.controller;

import com.bosonit.block12mongodb.application.PersonaServiceImpl;
import com.bosonit.block12mongodb.controller.dto.PersonaInputDTO;
import com.bosonit.block12mongodb.controller.dto.PersonaOutputDTO;
import com.bosonit.block12mongodb.controller.mapper.IPersonaMapper;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
@Validated
public class PersonaController {
    @Autowired
    PersonaServiceImpl personaService;

    IPersonaMapper mapper = Mappers.getMapper(IPersonaMapper.class);

    @PostMapping
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody @Valid PersonaInputDTO persona) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.addPersona(persona));

    }


    @GetMapping("{id}")
    public ResponseEntity<PersonaOutputDTO> getPersona(@PathVariable String id) {
        return ResponseEntity.ok(personaService.getPersona(id));
    }


    @GetMapping
    public ResponseEntity<List<PersonaOutputDTO>> getAllPersonas(@RequestParam int pageNumber,
                                                                 @RequestParam(required = false, defaultValue = "5") int pageSize
    ) {
        return new ResponseEntity<>(personaService.getAllPersonas(pageNumber, pageSize), HttpStatus.OK);
    }

    //Método que devuelve la lista con todas las personas que coinciden con el nombre pasado como parámetro
    @GetMapping("/user/{user}")
    public ResponseEntity<List<PersonaOutputDTO>> getPersonaByUser(@PathVariable String user) { //FIXME: pasar personas a OutputDTO --> Una forma sería con el mapper, y otra directamente utilizando la conversion de flujo de datos .stream()
        List<PersonaOutputDTO> listaP1 = personaService.getPersonas(user).stream().map(persona -> persona.personaToPersonaOutputDTO()).toList();
        return new ResponseEntity<>(listaP1, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updatePersona(@PathVariable String id, @RequestBody PersonaInputDTO personaInputDTO) {
        try {
            //comprobamos que la persona esté creada en el metodo update del servicio, si no lanza excepcion
            return ResponseEntity.ok().body(personaService.updatePersona(id, personaInputDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePersona(@PathVariable String id) { //TODO: no muestra la persona borrada por el body
        try {
            //si en vez de devolver el codigo OK, devolvemos un 204 (NO CONTENT) y no devuelve el objeto persona
            return ResponseEntity.status(HttpStatus.OK).body(personaService.deletePersona(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
