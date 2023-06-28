package com.bosonit.block11uploaddownloadfiles.fichero.domain;

import com.bosonit.block11uploaddownloadfiles.fichero.controller.dto.FicheroOutputDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fichero")
public class Fichero {
    @Id
    @Column(name = "id_fichero")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer idFichero;

    String nombre;

    Date fecha;

    String categoria;

    public FicheroOutputDTO ficheroToFicheroOutputDTO (){
        FicheroOutputDTO ficheroOutputDTO = new FicheroOutputDTO();
        ficheroOutputDTO.setId(this.idFichero);
        ficheroOutputDTO.setNombre(this.nombre);
        ficheroOutputDTO.setFecha(this.fecha);
        ficheroOutputDTO.setCategoria(this.categoria);

        return ficheroOutputDTO;
    }
}
