package com.gestion.empleados;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.empleados.modelo.Empleado;

@SpringBootTest
class GestionEmpleadosBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	public class EmpleadoTest {
	    @Test
	    public void testEmpleadoSerialization() throws Exception {
	        Empleado empleado = new Empleado();
	        // Configurar empleado...

	        ObjectMapper mapper = new ObjectMapper();
	        String json = mapper.writeValueAsString(empleado);
	        assertNotNull(json);
	        System.out.println(json);
	    }
	}

	

}
