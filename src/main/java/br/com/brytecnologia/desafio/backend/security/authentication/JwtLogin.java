package br.com.brytecnologia.desafio.backend.security.authentication;

import org.hibernate.validator.constraints.NotEmpty;

public class JwtLogin {

	@NotEmpty(message = "Senha não pode ser vazia.")
	private String senha;

	@NotEmpty(message = "Usuario não pode ser vazio.")
	private String usuario;

	public JwtLogin() {
	}

	public String getSenha() {
		return senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [usuario=" + usuario + ", senha=" + senha + "]";
	}

}
