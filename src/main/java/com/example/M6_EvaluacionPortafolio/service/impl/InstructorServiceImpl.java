package com.example.M6_EvaluacionPortafolio.service.impl;

import com.example.M6_EvaluacionPortafolio.entity.Instructor;
import com.example.M6_EvaluacionPortafolio.repository.InstructorRepository;
import com.example.M6_EvaluacionPortafolio.service.InstructorService;
import com.example.M6_EvaluacionPortafolio.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor no encontrado con id: " + id));
    }

    @Override
    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor update(Long id, Instructor instructor) {
        Instructor existing = findById(id);
        existing.setNombre(instructor.getNombre());
        existing.setEspecialidad(instructor.getEspecialidad());
        existing.setCursos(instructor.getCursos());
        return instructorRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Instructor existing = findById(id);
        instructorRepository.delete(existing);
    }
}
