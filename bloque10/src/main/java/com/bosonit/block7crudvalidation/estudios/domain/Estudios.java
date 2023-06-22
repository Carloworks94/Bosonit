package com.bosonit.block7crudvalidation.estudios.domain;

import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosSimpleOutputDTO;
import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDTO;
import com.bosonit.block7crudvalidation.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.List;
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
    @Column(name = "id_study")
    Integer idStudy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    Profesor profesor;
    @ManyToMany(mappedBy = "estudios", fetch = FetchType.LAZY)
    List<Student> students;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comment;
    @Column(name = "initialDate")
    Date initialDate;
    @Column(name = "finishDate")
    Date finishDate;


    public Estudios(EstudiosInputDTO estudiosInputDTO) {
        this.asignatura = estudiosInputDTO.getAsignatura();
        this.comment = estudiosInputDTO.getComment();
        this.initialDate = estudiosInputDTO.getInitialDate();
        this.finishDate = estudiosInputDTO.getFinishDate();
    }

    public EstudiosOutputDTO estudiosToEstudiosOutputDTO() {
        if (this.students == null)
            return new EstudiosOutputDTO(this.idStudy, this.profesor.getIdProfesor(), this.asignatura, this.comment, this.initialDate, this.finishDate);
        else
            return new EstudiosOutputDTO(this.idStudy, this.profesor.getIdProfesor(),
                    this.students.stream().map(student -> student.studentToStudentSimpleOutputDTO()).toList(),
                    this.asignatura, this.comment, this.initialDate, this.finishDate);
    }

    public EstudiosSimpleOutputDTO estudiosToEstudiosSimpleOutputDTO(){
        return new EstudiosSimpleOutputDTO(this.idStudy, this.asignatura,
                    this.comment, this.initialDate, this.finishDate);
    }
}
