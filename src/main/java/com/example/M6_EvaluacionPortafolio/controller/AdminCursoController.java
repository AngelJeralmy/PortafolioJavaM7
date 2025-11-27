package com.example.M6_EvaluacionPortafolio.controller;

import com.example.M6_EvaluacionPortafolio.service.CursoService;
import com.example.M6_EvaluacionPortafolio.service.InstructorService;
import com.example.M6_EvaluacionPortafolio.entity.Curso;
import com.example.M6_EvaluacionPortafolio.entity.Instructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/cursos")
public class AdminCursoController {

    private final CursoService cursoService;
    private final InstructorService instructorService;

    public AdminCursoController(CursoService cursoService, InstructorService instructorService) {
        this.cursoService = cursoService;
        this.instructorService = instructorService;
    }

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAll());
        return "admin/cursos";
    }

    @GetMapping("/crear")
    public String crearCurso(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("instructores", instructorService.findAll());
        return "admin/crearCurso";
    }

    @PostMapping("/crear")
    public String guardarCurso(
            @ModelAttribute Curso curso,
            @RequestParam("instructorId") Long instructorId) {

        // Buscar el instructor y asociarlo manualmente
        Instructor instructor = instructorService.findById(instructorId);
        curso.setInstructor(instructor);

        cursoService.save(curso);

        return "redirect:/admin/cursos";
    }

    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable Long id, Model model) {
        model.addAttribute("curso", cursoService.findById(id));
        model.addAttribute("instructores", instructorService.findAll());
        return "admin/editarCurso";
    }

    @PostMapping("/editar/{id}")
    public String actualizarCurso(
            @PathVariable Long id,
            @ModelAttribute Curso curso,
            @RequestParam("instructorId") Long instructorId) {

        Instructor instructor = instructorService.findById(instructorId);
        curso.setInstructor(instructor);

        cursoService.update(id, curso);

        return "redirect:/admin/cursos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        cursoService.delete(id);
        return "redirect:/admin/cursos";
    }
}
