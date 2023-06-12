package com.bosonit.block7crudvalidation.profesor.domain;

import com.bosonit.block7crudvalidation.persona.domain.Persona;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorInputDTO;
import com.bosonit.block7crudvalidation.profesor.controller.dto.ProfesorOutputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int idProfesor; //FIXME: hacerlo incremental con String

    @OneToOne
    @JoinColumn(name = "idPersona")
    Persona persona;

    @Column(name = "comentarios")
    String coments;

    @Column(name = "rama")
    String branch;

    public Profesor (ProfesorInputDTO profesorInputDTO){
        this.coments = profesorInputDTO.getComents();
        this.branch = profesorInputDTO.getBranch();
    }

    public ProfesorOutputDTO profesorToProfesorOutputDTO (){
        return new ProfesorOutputDTO(this.idProfesor, this.persona, this.coments, this.branch);
    }

}
