package com.gestion.empleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gestion.empleados.dto.servicio.EmpleadoServicio;
import com.gestion.empleados.modelo.Cliente;
import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.modelo.Rol;
import com.gestion.empleados.repositorio.ClienteRepositorio;
import com.gestion.empleados.repositorio.EmpleadoRepositorio;

@SpringBootApplication(scanBasePackages = "com.gestion.empleados")
public class GestionEmpleadosBackendApplication {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    //@Autowired
   // private EmpleadoServicio empleadoServicice;

	public static void main(String[] args) {
		SpringApplication.run(GestionEmpleadosBackendApplication.class, args);
		
	}
//##################INICIALIZAR DATOS DE LA APLICACIÓN#################
//#####################################################################
	   public void run(String... args) throws Exception {
	        try {
	            // Eliminar todos los datos de la base de datos
	            empleadoRepositorio.deleteAll();
	            clienteRepositorio.deleteAll();
	            System.out.println("#######################\nDatos de la base de datos eliminados correctamente.");

	            // Usuario 1 - Rol USER
	            Empleado usuario1 = new Empleado();
	            usuario1.setNombre("usuario");
	            usuario1.setApellido("anonimo");
	            usuario1.setUsuario("user");
	            usuario1.setEmail("usuario@example.com");
	            usuario1.setPassword(passwordEncoder.encode("password123"));
	            usuario1.getRoles().add(Rol.USER);

	            // Guardar usuario1
	            empleadoRepositorio.save(usuario1);
	            System.out.println("Usuario1 (user) creado");

	            // Usuario 2 - Rol ADMIN
	            Empleado usuario2 = new Empleado();
	            usuario2.setNombre("admin");
	            usuario2.setApellido("");
	            usuario2.setUsuario("admin");
	            usuario2.setEmail("admin@example.com");
	            usuario2.setPassword(passwordEncoder.encode("admin"));
	            usuario2.getRoles().add(Rol.ADMIN);
	            
	            // Guardar usuario2
	            empleadoRepositorio.save(usuario2);
	            System.out.println("Usuario2 (admin) creado");

	            
	            
	            // Crear y guardar clientes de prueba asociados a usuario1
	            Cliente cliente1 = new Cliente();
	            cliente1.setNombre("Juan");
	            cliente1.setApellidos("Dominguez");
	            cliente1.setDni("12345678A");
	            cliente1.setEmail("juan@example.com");
	            cliente1.setComercializadora("Endesa");
	            cliente1.setNumeroCUPS("ES0031");
	            cliente1.setTelefono("123456789");
	            cliente1.setEmpleado(usuario1); // Asociar cliente1 con usuario1
	            clienteRepositorio.save(cliente1);
	            System.out.println("Cliente1 asociado a Usuario1 creado");

	            Cliente cliente2 = new Cliente();
	            cliente2.setNombre("María");
	            cliente2.setApellidos("García");
	            cliente2.setDni("87654321B");
	            cliente2.setEmail("maria@example.com");
	            cliente2.setComercializadora("Iberdrola");
	            cliente1.setNumeroCUPS("ES008310");
	            cliente2.setTelefono("987654321");
	            cliente2.setEmpleado(usuario1); // Asociar cliente2 con usuario1
	            clienteRepositorio.save(cliente2);
	            System.out.println("Cliente2 asociado a Usuario1 creado");


	        } catch (Exception e) {
	            System.out.println("ERROR AL CREAR DATOS DE PRUEBA");
	        }
	    }
}
