package com.bosonit.block10cors.profesor.controller;

import com.bosonit.block10cors.profesor.application.ProfesorServiceImpl;
import com.bosonit.block10cors.profesor.controller.dto.ProfesorInputDTO;
import com.bosonit.block10cors.profesor.controller.dto.ProfesorOutputDTO;
import com.bosonit.block10cors.profesor.controller.dto.ProfesorSimpleOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorServiceImpl profesorService;


    @PostMapping
    public ResponseEntity<ProfesorOutputDTO> addProfesor (@RequestBody ProfesorInputDTO profesorInputDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorService.addProfesor(profesorInputDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProfesor (@PathVariable int id,
                                                          @RequestParam(defaultValue = "simple") String outputType){
        if (outputType.equals("simple")) {
            ProfesorSimpleOutputDTO p  = profesorService.getSimpleProfesor(id);
            return ResponseEntity.status(HttpStatus.OK).body(p);
        }else if (outputType.equals("full"))
            return ResponseEntity.status(HttpStatus.OK).body(profesorService.getProfesor(id));
        else
            return new ResponseEntity<>("Valor de parametro incorrecto", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<ProfesorOutputDTO>> getAllProfesors(){
        return ResponseEntity.status(HttpStatus.OK).body(profesorService.getAllProfesor());
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfesorOutputDTO> updateProfesor (@PathVariable int id,
                                                             @RequestBody ProfesorInputDTO profesorInputDTO){
        return ResponseEntity.status(HttpStatus.OK).body(profesorService.updateProfesor(id, profesorInputDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProfesorOutputDTO> deleteProfesor (@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(profesorService.deleteProfesor(id));
    }


}
