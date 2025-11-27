package com.example.M6_EvaluacionPortafolio.repository;

import com.example.M6_EvaluacionPortafolio.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByEmail(String email);
    boolean existsByEmail(String email);
}
