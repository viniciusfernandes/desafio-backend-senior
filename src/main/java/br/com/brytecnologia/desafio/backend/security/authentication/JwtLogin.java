package br.com.brytecnologia.desafio.backend.security.authentication;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class JwtLogin {

	@NotEmpty(message = "Username não pode ser vazio.")
	@Email(message = "Username inválido.")
	private String username;

	@NotEmpty(message = "Password não pode ser vazio.")
	private String password;

	public JwtLogin() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [username =" + username + ", password=" + password + "]";
	}

}
