package com.StrattonApp.Backend.config;

import com.StrattonApp.Backend.repository.EmpleadoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InicializarDatos implements CommandLineRunner {

    private final EmpleadoRepository empleadoRepository;
  //  private final AsesoriaRepository asesoriaRepository;
    private final PasswordEncoder passwordEncoder;

    //, AsesoriaRepository asesoriaRepository
    public InicializarDatos(EmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder) {
        this.empleadoRepository = empleadoRepository;
   //     this.asesoriaRepository = asesoriaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        inicializarEmpleados();
    }

    private void inicializarEmpleados() {
        // Verificar si ya existen empleados
        if (empleadoRepository.count() == 0) {
       
        }
    }
}
