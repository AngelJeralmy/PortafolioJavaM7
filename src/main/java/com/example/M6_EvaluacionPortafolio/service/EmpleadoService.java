package com.example.M6_EvaluacionPortafolio.service;

import com.example.M6_EvaluacionPortafolio.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAll();
    Empleado findById(Long id);
    Optional<Empleado> findByEmail(String email);
    Empleado save(Empleado empleado);
    Empleado update(Long id, Empleado empleado);
    void delete(Long id);
}
