package com.bosonit.block7crudvalidation.profesor.controller;

import com.bosonit.block7crudvalidation.profesor.application.ProfesorServiceImpl;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorInputDTO;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorOutputDTO;
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
    public ResponseEntity<ProfesorOutputDTO> getProfesor (@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(profesorService.getProfesor(id));
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
