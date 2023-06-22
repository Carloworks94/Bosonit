package com.bosonit.block7crudvalidation.student.repository;

import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.plaf.synth.SynthUI;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    Student findByPersona (Persona persona);

//    @Query("SELECT s.profesor. FROM Student s WHERE s.id_student = :studentId")
//    Profesor findProfesorByStudentId(@Param("studentId") Integer studentId);
}
