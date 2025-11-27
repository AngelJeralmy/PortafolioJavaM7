package com.example.M6_EvaluacionPortafolio.security;

import com.example.M6_EvaluacionPortafolio.entity.Empleado;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Empleado empleado;

    public CustomUserDetails(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + empleado.getRole().name()));
    }

    @Override
    public String getPassword() {
        return empleado.getPassword();
    }

    @Override
    public String getUsername() {
        return empleado.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
