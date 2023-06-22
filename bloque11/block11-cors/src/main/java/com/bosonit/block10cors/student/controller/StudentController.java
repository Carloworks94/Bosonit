package com.bosonit.block10cors.student.controller;

import com.bosonit.block10cors.student.application.StudentServiceImpl;
import com.bosonit.block10cors.student.controller.dto.StudentInputDTO;
import com.bosonit.block10cors.student.controller.dto.StudentOutputDTO;
import com.bosonit.block10cors.student.controller.dto.StudentSimpleOutputDTO;
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

    @PutMapping("/estudiosAdd/{id}")
    public ResponseEntity<StudentOutputDTO> addEstudiosToStudent(@PathVariable Integer id, @RequestBody List<Integer> lEstudiosId) {
        StudentOutputDTO studentOutputDTO = studentService.addEstudiosToStudent(id, lEstudiosId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentOutputDTO);
    }

    @PutMapping("/estudiosDelete/{id}")
    public ResponseEntity<StudentOutputDTO> deleteEstudiosToStudent(@PathVariable Integer id, @RequestBody List<Integer> lEstudiosId) {
        StudentOutputDTO studentOutputDTO = studentService.deleteEstudiosToStudent(id, lEstudiosId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentOutputDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) throws Exception {
        if (outputType.equals("simple")) {
            StudentSimpleOutputDTO e  = studentService.getSimpleStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body(e);
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
