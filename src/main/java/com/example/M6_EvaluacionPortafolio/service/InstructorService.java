package com.example.M6_EvaluacionPortafolio.service;

import com.example.M6_EvaluacionPortafolio.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> findAll();
    Instructor findById(Long id);
    Instructor save(Instructor instructor);
    Instructor update(Long id, Instructor instructor);
    void delete(Long id);
}
