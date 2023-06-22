package com.bosonit.block10cors.persona.repository;


import com.bosonit.block10cors.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findAllByUserPersona(String user);

    boolean existsByCompanyEmail (String companyEmail);
    @Query(value = "SELECT COUNT(*) FROM Persona")
    int getTotal();
}
