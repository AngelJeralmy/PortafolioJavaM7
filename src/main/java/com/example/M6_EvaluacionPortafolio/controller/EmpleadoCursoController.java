package com.example.M6_EvaluacionPortafolio.controller;

import com.example.M6_EvaluacionPortafolio.service.CursoService;
import com.example.M6_EvaluacionPortafolio.service.InscripcionService;
import com.example.M6_EvaluacionPortafolio.service.EmpleadoService;
import com.example.M6_EvaluacionPortafolio.entity.Empleado;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleado/cursos")
public class EmpleadoCursoController {

    private final CursoService cursoService;
    private final EmpleadoService empleadoService;
    private final InscripcionService inscripcionService;

    public EmpleadoCursoController(CursoService cursoService,
                                   EmpleadoService empleadoService,
                                   InscripcionService inscripcionService) {
        this.cursoService = cursoService;
        this.empleadoService = empleadoService;
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAll());
        return "empleado/cursos";
    }

    @PostMapping("/inscribir/{cursoId}")
    public String inscribir(@PathVariable Long cursoId, Authentication auth) {
        Empleado empleado = empleadoService.findByEmail(auth.getName()).orElseThrow();
        inscripcionService.register(empleado.getId(), cursoId);
        return "redirect:/empleado/cursos?inscripcion=ok";
    }
}
