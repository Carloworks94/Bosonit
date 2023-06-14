package com.bosonit.block7crudvalidation.student.controller;

import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block7crudvalidation.student.application.StudentServiceImpl;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentInputDTO;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDTO;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDTO> addStudent(@RequestBody @Valid StudentInputDTO studentInputDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(studentInputDTO));
    }

    @PostMapping("/estudios/{id}") //FIXME: POST o PUT?
    public ResponseEntity<StudentOutputDTO> addEstudiosToStudent(@PathVariable Integer id, @RequestBody List<Integer> lEstudiosId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addEstudiosToStudent(id, lEstudiosId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) throws Exception {
        if (outputType.equals("simple")) {
            StudentSimpleOutputDTO studentSimpleOutputDTO = studentService.getSimpleStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getSimpleStudent(id));
        }else if (outputType.equals("full"))
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudent(id));
        else
            return new ResponseEntity<>("Valor de parametro incorrecto", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<StudentOutputDTO>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentOutputDTO> updateStudent(@PathVariable int id, @RequestBody StudentInputDTO studentInputDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id, studentInputDTO));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<StudentOutputDTO> deleteStudent(@PathVariable int id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.deleteStudent(id));
    }

}
