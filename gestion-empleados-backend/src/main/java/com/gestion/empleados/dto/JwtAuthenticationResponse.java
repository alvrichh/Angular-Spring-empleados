package com.gestion.empleados.dto;

/**
 * Respuesta de autenticación JWT que contiene el token JWT generado.
 */
public class JwtAuthenticationResponse {

	/**
	 * Token JWT.
	 */
	private String token;

	/**
	 * Constructor para crear una nueva instancia de JwtAuthenticationResponse con
	 * el token proporcionado.
	 *
	 * @param token Token JWT.
	 */
	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}

	/**
	 * Obtiene el token JWT.
	 *
	 * @return Token JWT.
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static JwtAuthenticationResponseBuilder builder() {
		return new JwtAuthenticationResponseBuilder();
	}

	public static class JwtAuthenticationResponseBuilder {

		private String token;

		public JwtAuthenticationResponseBuilder token(String token) {
			this.token = token;
			return this;
		}

		public JwtAuthenticationResponse build() {
			return new JwtAuthenticationResponse(token);
		}
	}
}
