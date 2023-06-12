package com.bosonit.block7crudvalidation.estudios.domain;

import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Getter
@Setter  //usamos getter y setter en vez de @Data ya que lombok provoca un error en relaciones OneToMany
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudios")
public class Estudios {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer idStudy;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "profesor_id")
//    Profesor profesor;
    @ManyToOne(cascade = CascadeType.ALL)
    Student student;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comment;
    @Column(name = "initialDate")
    Date initialDate;
    @Column(name = "finishDate")
    Date finishDate;

    public Estudios (EstudiosInputDTO estudiosInputDTO){
        this.asignatura = estudiosInputDTO.getAsignatura();
        this.comment = estudiosInputDTO.getComment();
        this.initialDate = estudiosInputDTO.getInitialDate();
        this.finishDate = estudiosInputDTO.getFinishDate();
    }

    public EstudiosOutputDTO estudiosToEstudiosOutputDTO (){
        return new EstudiosOutputDTO(this.idStudy,this.student,this.asignatura,this.comment,this.initialDate,this.finishDate);
    }
}
