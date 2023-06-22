package com.bosonit.block7crudvalidation.estudios.controller;

import com.bosonit.block7crudvalidation.estudios.application.EstudiosServiceImpl;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosSimpleOutputDTO;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorSimpleOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudiosController {
    @Autowired
    EstudiosServiceImpl estudiosService;

    @PostMapping
    public ResponseEntity<EstudiosOutputDTO> addEstudios (@RequestBody EstudiosInputDTO estudiosInputDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(estudiosService.addEstudios(estudiosInputDTO));
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getEstudios (@PathVariable int id,
                                          @RequestParam(defaultValue = "simple") String outputType){
        if (outputType.equals("simple")) {
            EstudiosSimpleOutputDTO e = estudiosService.getSimpleEstudios(id);
            return ResponseEntity.status(HttpStatus.OK).body(e);
        }else if (outputType.equals("full"))
            return ResponseEntity.status(HttpStatus.OK).body(estudiosService.getEstudios(id));
        else
            return new ResponseEntity<>("Valor de parametro incorrecto", HttpStatus.BAD_REQUEST);
    }

    //Método que devuelve todas las asignaturas a un estudiante
    @GetMapping("/student/{id}")
    public ResponseEntity<List<String>> getAsignaturasStudent (@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(estudiosService.getAsignaturasStudent(id));
    }

    @GetMapping
    public ResponseEntity<List<EstudiosOutputDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(estudiosService.getAllEstudios());
    }

    @PutMapping("{id}")
    public ResponseEntity<EstudiosOutputDTO> updateEstudios(@PathVariable int id,
                                                            @RequestBody EstudiosInputDTO estudiosInputDTO){
        return ResponseEntity.status(HttpStatus.OK).body(estudiosService.updateEstudios(id, estudiosInputDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EstudiosOutputDTO> deleteEstudios (@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(estudiosService.deleteEstudios(id));
    }
}
