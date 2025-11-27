package com.example.M6_EvaluacionPortafolio.security;

/**
 * Roles de la aplicación.
 * Los nombres aquí deben corresponder a los usados en las comprobaciones de seguridad
 * (por ejemplo .hasRole("ADMIN") espera que el Role sea "ADMIN" — sin el prefijo "ROLE_").
 */
public enum Role {
    ADMIN,
    EMPLEADO
}
