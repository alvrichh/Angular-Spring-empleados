package com.gestion.empleados.dto.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gestion.empleados.dto.EmpleadoDTO;
import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.repositorio.EmpleadoRepositorio;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio {

	@Autowired
	private EmpleadoRepositorio empleadorepositorio;

	/**
	 * Implementación del servicio UserDetailsService que carga un usuario por su
	 * nombre de usuario.
	 *
	 * @return Detalles del usuario.
	 * @throws UsernameNotFoundException Si el usuario no es encontrado.
	 */
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return (UserDetails) empleadorepositorio.findByUsuario(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}
		};
	}

	/**
	 * Obtiene todos los usuarios y los convierte en una lista de UsuarioDTO.
	 *
	 * @return Lista de UsuarioDTO.
	 */

	/**
	 * Obtiene un usuario por su ID.
	 *
	 * @param userId ID del usuario a obtener.
	 * @return Lista que contiene el usuario si se encuentra, o una lista vacía si
	 *         no.
	 */
	@Override
	public List<Empleado> getUserById(Long userId) {
		// Se asume que hay un método en el repositorio que retorna un Optional<Usuario>
		Optional<Empleado> optionalUser = empleadorepositorio.findById(userId);

		// Verifica si el usuario existe y retorna una lista con ese usuario o una lista
		// vacía si no se encuentra
		return optionalUser.map(List::of).orElse(List.of());
	}

	public List<EmpleadoDTO> getAllUsers() {
		return empleadorepositorio.findAll().stream()
				.map(empleado -> new EmpleadoDTO(empleado.getUsuario(), empleado.getRoles()))
				.collect(Collectors.toList());
	}
}
