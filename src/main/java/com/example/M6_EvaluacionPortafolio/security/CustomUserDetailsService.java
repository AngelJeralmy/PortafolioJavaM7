package com.example.M6_EvaluacionPortafolio.security;

import com.example.M6_EvaluacionPortafolio.entity.Empleado;
import com.example.M6_EvaluacionPortafolio.repository.EmpleadoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmpleadoRepository empleadoRepository;

    public CustomUserDetailsService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Empleado empleado = empleadoRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new CustomUserDetails(empleado);
    }
}
