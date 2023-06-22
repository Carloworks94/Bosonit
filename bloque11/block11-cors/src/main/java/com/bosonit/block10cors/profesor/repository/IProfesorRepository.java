package com.bosonit.block10cors.profesor.repository;

import com.bosonit.block10cors.persona.domain.Persona;
import com.bosonit.block10cors.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfesorRepository extends JpaRepository<Profesor, Integer> {
    Profesor findByPersona (Persona persona);
}
