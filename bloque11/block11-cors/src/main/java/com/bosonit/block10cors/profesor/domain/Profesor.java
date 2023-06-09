package com.bosonit.block10cors.profesor.domain;

import com.bosonit.block10cors.persona.domain.Persona;
import com.bosonit.block10cors.profesor.controller.dto.ProfesorInputDTO;
import com.bosonit.block10cors.profesor.controller.dto.ProfesorOutputDTO;
import com.bosonit.block10cors.profesor.controller.dto.ProfesorSimpleOutputDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_profesor")
    int idProfesor; //FIXME: hacerlo incremental con String

    @OneToOne
    @JoinColumn(name = "id")
    Persona persona;

//    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
//    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    List<Student> students;

    @Column(name = "comentarios")
    String coments;

    @Column(name = "rama")
    String branch;

    public Profesor (ProfesorInputDTO profesorInputDTO){
        this.coments = profesorInputDTO.getComents();
        this.branch = profesorInputDTO.getBranch();
    }

    public ProfesorOutputDTO profesorToProfesorOutputDTO (){
        return new ProfesorOutputDTO(this.idProfesor, this.persona.personaToPersonaOutputDTO(), this.coments, this.branch);
    }

    public ProfesorSimpleOutputDTO profesorToProfesorSimpleOutputDTO (){
        return new ProfesorSimpleOutputDTO(this.idProfesor, this.coments, this.branch);
    }

}
