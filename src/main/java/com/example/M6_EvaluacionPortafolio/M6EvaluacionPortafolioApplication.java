package com.example.M6_EvaluacionPortafolio;

import com.example.M6_EvaluacionPortafolio.entity.Empleado;
import com.example.M6_EvaluacionPortafolio.entity.Instructor;
import com.example.M6_EvaluacionPortafolio.repository.EmpleadoRepository;
import com.example.M6_EvaluacionPortafolio.repository.InstructorRepository;
import com.example.M6_EvaluacionPortafolio.security.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class M6EvaluacionPortafolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(M6EvaluacionPortafolioApplication.class, args);
    }

    @Bean
    CommandLineRunner initInstructores(InstructorRepository instructorRepository) {
        return args -> {

            if (instructorRepository.count() == 0) {
                instructorRepository.save(Instructor.builder()
                        .nombre("Carlos Pérez")
                        .especialidad("Programación Java")
                        .build());

                instructorRepository.save(Instructor.builder()
                        .nombre("María González")
                        .especialidad("Bases de Datos")
                        .build());

                instructorRepository.save(Instructor.builder()
                        .nombre("Luis Ramírez")
                        .especialidad("Spring Boot")
                        .build());
            }
        };
    }
}
