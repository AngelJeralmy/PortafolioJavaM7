package com.example.M6_EvaluacionPortafolio.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        boolean isAdmin = authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean isEmpleado = authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLEADO"));

        if (isAdmin) {
            response.sendRedirect(request.getContextPath() + "/admin/cursos");
            return;
        }

        if (isEmpleado) {
            response.sendRedirect(request.getContextPath() + "/empleado/cursos");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/");
    }
}
