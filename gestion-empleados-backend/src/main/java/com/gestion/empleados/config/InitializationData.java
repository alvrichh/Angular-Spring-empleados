package com.gestion.empleados.config;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.repositorio.EmpleadoRepositorio;
import com.github.javafaker.Faker;

/**
 * Clase que inicializa datos de prueba para el perfil "demo" de la aplicación.
 */
@Profile("demo")
@Component
public class InitializationData implements CommandLineRunner {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Ejecuta la inicialización de datos de prueba al arrancar la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     * @throws Exception Si ocurre un error durante la inicialización.
     */
    @Override
    public void run(String... args) throws Exception {

        try {
            // Usuario 1 - Rol USER
            Empleado usuario1 = new Empleado();
            usuario1.setNombre("usuario");
            usuario1.setApellido("anonimo");
            usuario1.setUsuario("usuario");
            usuario1.setEmail("usuario@example.com");
            usuario1.setPassword(passwordEncoder.encode("password123"));
            empleadoRepositorio.save(usuario1);
            System.out.println("Usuario1 (user) creado");
        } catch (Exception e) {
            System.out.println("ERROR al crear Usuario USER");
        }

/**

        Faker faker = new Faker(new Locale("es"));
        for (int i = 0; i < 10; i++) { // Generar 10 productos ficticios
            Empleado producto = new Empleado();
            producto.setNombre(faker.dragonBall().character());
            producto.setApellido(faker.lorem().sentence());
            empleadoRepositorio.save(producto);
        }**/
    }
}
