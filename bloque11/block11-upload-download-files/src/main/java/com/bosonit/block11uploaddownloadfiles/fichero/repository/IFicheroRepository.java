package com.bosonit.block11uploaddownloadfiles.fichero.repository;

import com.bosonit.block11uploaddownloadfiles.fichero.domain.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IFicheroRepository extends JpaRepository<Fichero, Integer> {
    Fichero findByNombre(String nombre);

    @Query("SELECT f FROM Fichero f WHERE f.nombre = :nombre")
    Fichero findByNombreExacto(@Param("nombre") String nombre);
}
