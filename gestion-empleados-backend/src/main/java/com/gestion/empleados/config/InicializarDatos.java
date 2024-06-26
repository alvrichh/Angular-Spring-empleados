package com.gestion.empleados.config;

import com.StrattonApp.Backend.repository.EmpleadoRepository;
import com.gestion.empleados.modelo.Empleado;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InicializarDatos implements CommandLineRunner {

    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;

    public InicializarDatos(EmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder) {
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        inicializarEmpleados();
    }

    private void inicializarEmpleados() {
        // Verificar si ya existen empleados
    	System.out.println("Creando usuario admin##########");
    	System.out.println(empleadoRepository.count());
        if (empleadoRepository.count() == 0) {
            // Crear un conjunto de roles para el primer empleado (admin)
           // Set<Role> rolesAdmin = new HashSet<>();
          //  rolesAdmin.add(Role.ADMIN);

            // Crear un conjunto de roles para el segundo empleado (usuario)
         //   Set<Role> rolesUsuario = new HashSet<>();
         //   rolesUsuario.add(Role.USER);

            // Crear dos empleados de ejemplo
            Empleado admin = new Empleado();
            admin.setNombre("admin");
            admin.setApellido("admin");
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            empleadoRepository.save(admin);

            System.out.println(admin);

        }
    }
/**
    private void inicializarAsesorias() {
        // Verificar si ya existen asesorías
        if (asesoriaRepository.count() == 0) {
            // Crear asesorías de ejemplo
            Asesoria asesoria1 = new Asesoria();
            asesoria1.setNombre("Asesoría 1");
            asesoria1.setDescripcion("Descripción de la asesoría 1");
            asesoriaRepository.save(asesoria1);

            Asesoria asesoria2 = new Asesoria();
            asesoria2.setNombre("Asesoría 2");
            asesoria2.setDescripcion("Descripción de la asesoría 2");
            asesoriaRepository.save(asesoria2);
        }
    }**/
}
