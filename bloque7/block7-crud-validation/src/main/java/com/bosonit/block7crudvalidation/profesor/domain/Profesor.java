package com.bosonit.block7crudvalidation.profesor.domain;

import com.bosonit.block7crudvalidation.persona.domain.Persona;
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
    int id_profesor; //FIXME: hacerlo incremental con String

    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;

    @Column(name = "comentarios")
    String coments;

    @Column(name = "rama")
    String branch;

}
