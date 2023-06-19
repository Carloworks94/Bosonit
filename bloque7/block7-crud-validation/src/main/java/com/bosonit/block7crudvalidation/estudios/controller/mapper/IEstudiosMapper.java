package com.bosonit.block7crudvalidation.estudios.controller.mapper;

import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosSimpleOutputDTO;
import com.bosonit.block7crudvalidation.estudios.domain.Estudios;
import com.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;
import java.util.List;

@Mapper
public interface IEstudiosMapper {
//    @Mapping(target = "idProfesor", ignore = true)
//    @Mapping(target = "student", ignore = true)
//    EstudiosOutputDTO estudiosToEstudiosOutputDTO(Estudios estudios);

}
