package com.bosonit.block7crudvalidation.profesor.application;


import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorInputDTO;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorOutputDTO;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorSimpleOutputDTO;

import java.util.List;

public interface IProfesorService {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO);

    ProfesorOutputDTO getProfesor(int id);

    ProfesorSimpleOutputDTO getSimpleProfesor(int id);

    List<ProfesorOutputDTO> getAllProfesor();

    ProfesorOutputDTO updateProfesor(int id, ProfesorInputDTO profesorInputDTO);

    ProfesorOutputDTO deleteProfesor(int id);

}
