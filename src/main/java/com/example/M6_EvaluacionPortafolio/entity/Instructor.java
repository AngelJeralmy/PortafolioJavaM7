package com.example.M6_EvaluacionPortafolio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String especialidad;

    @OneToMany(mappedBy = "instructor")
    private List<Curso> cursos;
}
