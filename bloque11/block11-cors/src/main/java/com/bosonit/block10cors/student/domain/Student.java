package com.bosonit.block10cors.student.domain;

import com.bosonit.block10cors.estudios.domain.Estudios;
import com.bosonit.block10cors.persona.domain.Persona;
import com.bosonit.block10cors.profesor.domain.Profesor;
import com.bosonit.block10cors.student.controller.dto.StudentInputDTO;
import com.bosonit.block10cors.student.controller.dto.StudentOutputDTO;
import com.bosonit.block10cors.student.controller.dto.StudentSimpleOutputDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiantes")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id_student;
    @OneToOne
    @JoinColumn(name = "id")
    Persona persona;
    @Column(name = "horas_por_semana")
    Integer num_hours_week;
    @Column(name = "comentarios")
    String coments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Profesor profesor;
    @Column(name = "rama")
    String branch;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_estudios",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "study_id"))
    List<Estudios> estudios;

    public Student(StudentInputDTO studentInputDTO) {
        this.num_hours_week = studentInputDTO.getNum_hours_week();
        this.coments = studentInputDTO.getComents();
        this.branch = studentInputDTO.getBranch();
        this.estudios = studentInputDTO.getEstudios();
    }

    public StudentOutputDTO studentToStudentOutputDTO() {
        return new StudentOutputDTO(this.id_student, this.persona.personaToPersonaOutputDTO(), this.num_hours_week, this.coments, this.profesor.getIdProfesor(),
        this.branch, getEstudios().stream().map(estudios -> estudios.estudiosToEstudiosSimpleOutputDTO()).toList());
    }
    public StudentSimpleOutputDTO studentToStudentSimpleOutputDTO() {
        return new StudentSimpleOutputDTO(this.id_student, this.num_hours_week, this.coments, this.profesor.getIdProfesor(),
                this.branch);
    }

}
