package com.bosonit.block7crudvalidation.student.controller;

import com.bosonit.block7crudvalidation.student.application.StudentServiceImpl;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentInputDTO;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDTO;
import com.bosonit.block7crudvalidation.student.domain.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDTO> addStudent (@RequestBody StudentInputDTO studentInputDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(studentInputDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent (@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) throws Exception {
        if (outputType.equals("simple"))
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getSimpleStudent(id));
        else if (outputType.equals("full"))
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudent(id));
        else
            return new ResponseEntity<>("Valor de parametro incorrecto", HttpStatus.BAD_REQUEST);
    }


}
