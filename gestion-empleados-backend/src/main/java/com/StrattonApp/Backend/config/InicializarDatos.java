package com.StrattonApp.Backend.config;

import com.StrattonApp.Backend.repository.EmpleadoRepository;
import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.modelo.Role;

import java.util.HashSet;
import java.util.Set;

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
            // Crear un conjunto de roles para el primer empleado (admin)
            Set<Role> rolesAdmin = new HashSet<>();
            rolesAdmin.add(Role.ADMIN);

            // Crear un conjunto de roles para el segundo empleado (usuario)
            Set<Role> rolesUsuario = new HashSet<>();
            rolesUsuario.add(Role.USER);

            // Crear dos empleados de ejemplo
            Empleado admin = new Empleado();
            admin.setNombre("alvaro");
            admin.setApellido("rodriguez");
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(rolesAdmin);
            empleadoRepository.save(admin);
        }
    }
}
