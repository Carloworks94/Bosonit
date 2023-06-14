package com.bosonit.block7crudvalidation.estudios.application;

import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosOutputDTO;

import java.util.List;

public interface IEstudiosService {
    EstudiosOutputDTO addEstudios(EstudiosInputDTO estudiosInputDTO);
    EstudiosOutputDTO getEstudios(int id);

    List<EstudiosOutputDTO> getAllEstudios();

    EstudiosOutputDTO updateEstudios (int id, EstudiosInputDTO estudiosInputDTO);

    EstudiosOutputDTO deleteEstudios (int id);
}
