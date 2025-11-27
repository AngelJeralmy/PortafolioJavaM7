package com.example.M6_EvaluacionPortafolio.controller;

import org.springframework.ui.Model;
import com.example.M6_EvaluacionPortafolio.entity.Empleado;
import com.example.M6_EvaluacionPortafolio.security.Role;
import com.example.M6_EvaluacionPortafolio.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final EmpleadoService empleadoService;

    public AuthController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Empleado empleado,
                            @RequestParam("rolSeleccionado") String rolSeleccionado,
                            Model model) {

        if (empleadoService.findByEmail(empleado.getEmail()).isPresent()) {
            model.addAttribute("error", "El correo ya está registrado");
            return "registro";
        }

        if (rolSeleccionado.equals("ADMIN")) {
            empleado.setRole(Role.ADMIN);
        } else {
            empleado.setRole(Role.EMPLEADO);
        }

        empleadoService.save(empleado);

        model.addAttribute("mensaje", "Registro exitoso. Ahora puedes iniciar sesión.");
        return "login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
}
