package com.example.M6_EvaluacionPortafolio.controller;

import com.example.M6_EvaluacionPortafolio.entity.Curso;
import com.example.M6_EvaluacionPortafolio.entity.Inscripcion;
import com.example.M6_EvaluacionPortafolio.service.CursoService;
import com.example.M6_EvaluacionPortafolio.service.InscripcionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    private final CursoService cursoService;
    private final InscripcionService inscripcionService;

    public ApiController(CursoService cursoService, InscripcionService inscripcionService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
    }

    @GetMapping("/cursos")
    public List<Curso> getCursos() {
        return cursoService.findAll();
    }

    @PostMapping("/inscripciones")
    public Inscripcion inscribir(@RequestParam Long empleadoId,
                                 @RequestParam Long cursoId) {
        return inscripcionService.register(empleadoId, cursoId);
    }
}
