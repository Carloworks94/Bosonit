package com.bosonit.block7crudvalidation.estudios.domain;

import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosInputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosOutputDTO;
import com.bosonit.block7crudvalidation.estudios.controller.dto.EstudiosSimpleOutputDTO;
import com.bosonit.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter  //usamos getter y setter en vez de @Data ya que lombok provoca un error en relaciones OneToMany
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudios")
public class Estudios {
    @Id
    //FIXME: he cambiado Integer por int por el test de addEstudios (no sé si puede generar errores en el codigo, comprobarlo),
    //que daba el valor de null en vez de 0 al no ser una clase primitiva
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_study")
    int idStudy;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Profesor profesor;
    @ManyToMany(mappedBy = "estudios", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonIgnoreProperties("estudios")  //para evitar la recursion infinita al devolver el ResponseEntity en formato JSON
    //tal vez se puede evitar este error tb devolviendo un objeto studentOutputDTO nuevo sin mostrar la lista de estudios
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

    public EstudiosSimpleOutputDTO estudiosToEstudiosSimpleOutputDTO() {
        return new EstudiosSimpleOutputDTO(this.idStudy, this.asignatura,
                this.comment, this.initialDate, this.finishDate);
    }
}
