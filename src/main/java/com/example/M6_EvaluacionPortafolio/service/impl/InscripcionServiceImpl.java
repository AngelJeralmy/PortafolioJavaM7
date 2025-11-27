package com.example.M6_EvaluacionPortafolio.service.impl;

import com.example.M6_EvaluacionPortafolio.entity.Curso;
import com.example.M6_EvaluacionPortafolio.entity.Empleado;
import com.example.M6_EvaluacionPortafolio.entity.Inscripcion;
import com.example.M6_EvaluacionPortafolio.repository.CursoRepository;
import com.example.M6_EvaluacionPortafolio.repository.EmpleadoRepository;
import com.example.M6_EvaluacionPortafolio.repository.InscripcionRepository;
import com.example.M6_EvaluacionPortafolio.service.InscripcionService;
import com.example.M6_EvaluacionPortafolio.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EmpleadoRepository empleadoRepository;
    private final CursoRepository cursoRepository;

    public InscripcionServiceImpl(InscripcionRepository inscripcionRepository,
                                  EmpleadoRepository empleadoRepository,
                                  CursoRepository cursoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.empleadoRepository = empleadoRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Inscripcion> findAll() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion findById(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con id: " + id));
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion) {
        if (inscripcion.getFechaInscripcion() == null) {
            inscripcion.setFechaInscripcion(LocalDate.now());
        }
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public Inscripcion register(Long empleadoId, Long cursoId) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + empleadoId));
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + cursoId));

        Inscripcion inscripcion = Inscripcion.builder()
                .empleado(empleado)
                .curso(curso)
                .fechaInscripcion(LocalDate.now())
                .build();

        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public void delete(Long id) {
        Inscripcion existing = findById(id);
        inscripcionRepository.delete(existing);
    }
}
