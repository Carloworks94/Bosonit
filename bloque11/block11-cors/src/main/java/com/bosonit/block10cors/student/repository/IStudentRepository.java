package com.bosonit.block10cors.student.repository;

import com.bosonit.block10cors.persona.domain.Persona;
import com.bosonit.block10cors.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    Student findByPersona (Persona persona);

//    @Query("SELECT s.profesor. FROM Student s WHERE s.id_student = :studentId")
//    Profesor findProfesorByStudentId(@Param("studentId") Integer studentId);
}
