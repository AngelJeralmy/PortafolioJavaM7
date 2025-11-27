package com.example.M6_EvaluacionPortafolio.service.impl;

import com.example.M6_EvaluacionPortafolio.entity.Curso;
import com.example.M6_EvaluacionPortafolio.repository.CursoRepository;
import com.example.M6_EvaluacionPortafolio.service.CursoService;
import com.example.M6_EvaluacionPortafolio.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + id));
    }

    @Override
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso update(Long id, Curso curso) {
        Curso existing = findById(id);
        existing.setNombre(curso.getNombre());
        existing.setDescripcion(curso.getDescripcion());
        existing.setDuracionHoras(curso.getDuracionHoras());
        existing.setInstructor(curso.getInstructor());
        existing.setInscripciones(curso.getInscripciones());
        return cursoRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Curso existing = findById(id);
        cursoRepository.delete(existing);
    }
}
