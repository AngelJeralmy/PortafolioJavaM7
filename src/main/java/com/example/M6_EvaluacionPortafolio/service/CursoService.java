package com.example.M6_EvaluacionPortafolio.service;

import com.example.M6_EvaluacionPortafolio.entity.Curso;

import java.util.List;

public interface CursoService {
    List<Curso> findAll();
    Curso findById(Long id);
    Curso save(Curso curso);
    Curso update(Long id, Curso curso);
    void delete(Long id);
}
