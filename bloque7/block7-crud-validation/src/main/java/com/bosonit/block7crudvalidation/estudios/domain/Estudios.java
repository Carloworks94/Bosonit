package com.bosonit.block7crudvalidation.estudios.domain;

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
    Integer id_study;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "profesor_id")
//    Profesor profesor;
    @ManyToOne(cascade = CascadeType.ALL)
    Student student;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comment;
    @Column(name = "initial_date")
    Date initial_date;
    @Column(name = "finish_date")
    Date finish_date;
}
