package com.bosonit.block7crudvalidation.estudios.repository;

import com.bosonit.block7crudvalidation.estudios.domain.Estudios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEstudiosRepository extends JpaRepository<Estudios, Integer> {
}
