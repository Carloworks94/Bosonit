package com.bosonit.block7crud.persona.repository;


import com.bosonit.block7crud.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonaRepository extends JpaRepository<com.bosonit.block7crud.persona.domain.Persona, Integer> {
    List<Persona> findAllByNombre(String nombre);

    @Query(value = "SELECT COUNT(*) FROM Persona")
    int getTotal();
}
