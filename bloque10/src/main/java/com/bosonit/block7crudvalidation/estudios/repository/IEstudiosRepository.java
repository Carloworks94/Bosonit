package com.bosonit.block7crudvalidation.estudios.repository;

import com.bosonit.block7crudvalidation.estudios.domain.Estudios;
import com.bosonit.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEstudiosRepository extends JpaRepository<Estudios, Integer> {

}
