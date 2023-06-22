package com.bosonit.block10cors.estudios.application;

import com.bosonit.block10cors.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block10cors.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block10cors.estudios.controller.dto.EstudiosSimpleOutputDTO;

import java.util.List;

public interface IEstudiosService {
    EstudiosOutputDTO addEstudios(EstudiosInputDTO estudiosInputDTO);
    EstudiosOutputDTO getEstudios(int id);
    EstudiosSimpleOutputDTO getSimpleEstudios(int id);
    List<EstudiosOutputDTO> getAllEstudios();

    List<String> getAsignaturasStudent(int id);
    EstudiosOutputDTO updateEstudios (int id, EstudiosInputDTO estudiosInputDTO);

    EstudiosOutputDTO deleteEstudios (int id);
}
