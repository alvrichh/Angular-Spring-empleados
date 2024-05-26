package com.gestion.empleados.config;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gestion.empleados.modelo.Cliente;
import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.modelo.Rol;
import com.gestion.empleados.repositorio.ClienteRepositorio;
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
    private ClienteRepositorio clienteRepositorio;
    
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
        	empleadoRepositorio.deleteAll();
            Empleado usuario1 = new Empleado();
            usuario1.setNombre("usuario");
            usuario1.setApellido("anonimo");
            usuario1.setUsuario("usuario");
            usuario1.setEmail("usuario@example.com");
            usuario1.setPassword(passwordEncoder.encode("password123"));
            usuario1.getRoles().add(Rol.USER);
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
    
        // Eliminar todos los clientes existentes para evitar duplicados
        clienteRepositorio.deleteAll();

        // Crear y guardar clientes de prueba
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan Pérez");
        cliente1.setDni("12345678A");
        cliente1.setEmail("juan@example.com");
        cliente1.setComercializadora("Endesa");
        cliente1.setTelefono("123456789");
        clienteRepositorio.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("María García");
        cliente2.setDni("87654321B");
        cliente2.setEmail("maria@example.com");
        cliente2.setComercializadora("Iberdrola");
        cliente2.setTelefono("987654321");
        clienteRepositorio.save(cliente2);

}}
