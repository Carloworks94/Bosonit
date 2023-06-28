package com.bosonit.block11uploaddownloadfiles.fichero.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FicheroOutputDTO {
    Integer id;
    String nombre;

    Date fecha;

    String categoria;
}
