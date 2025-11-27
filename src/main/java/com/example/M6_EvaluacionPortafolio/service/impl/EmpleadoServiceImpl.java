package com.example.M6_EvaluacionPortafolio.service.impl;

import com.example.M6_EvaluacionPortafolio.entity.Empleado;
import com.example.M6_EvaluacionPortafolio.repository.EmpleadoRepository;
import com.example.M6_EvaluacionPortafolio.service.EmpleadoService;
import com.example.M6_EvaluacionPortafolio.exceptions.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
                               BCryptPasswordEncoder passwordEncoder) {
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado findById(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
    }

    @Override
    public Optional<Empleado> findByEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

    @Override
    public Empleado save(Empleado empleado) {
        // codifica contraseña antes de guardar
        if (empleado.getPassword() != null) {
            empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado update(Long id, Empleado empleado) {
        Empleado existing = findById(id);
        existing.setNombre(empleado.getNombre());
        existing.setEmail(empleado.getEmail());
        // si se provee password, actualizarla (y codificar)
        if (empleado.getPassword() != null && !empleado.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(empleado.getPassword()));
        }
        existing.setRole(empleado.getRole());
        existing.setInscripciones(empleado.getInscripciones());
        return empleadoRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Empleado existing = findById(id);
        empleadoRepository.delete(existing);
    }
}
