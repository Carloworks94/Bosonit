package com.bosonit.block7crudvalidation.student.repository;

import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.plaf.synth.SynthUI;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    Student findByPersona (Persona persona);
}
