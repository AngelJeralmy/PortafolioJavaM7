package com.example.M6_EvaluacionPortafolio.service;

import com.example.M6_EvaluacionPortafolio.entity.Inscripcion;

import java.util.List;

public interface InscripcionService {
    List<Inscripcion> findAll();
    Inscripcion findById(Long id);
    Inscripcion save(Inscripcion inscripcion);
    /**
     * Registra (crea) una inscripción asociando empleado y curso por sus ids
     * y devolviendo la entidad Inscripcion creada.
     */
    Inscripcion register(Long empleadoId, Long cursoId);
    void delete(Long id);
}
