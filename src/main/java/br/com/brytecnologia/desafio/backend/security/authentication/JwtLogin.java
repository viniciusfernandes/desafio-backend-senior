package br.com.brytecnologia.desafio.backend.security.authentication;

import org.hibernate.validator.constraints.NotEmpty;

public class JwtLogin {

	@NotEmpty(message = "Usuario não pode ser vazio.")
	private String usuario;

	@NotEmpty(message = "Senha não pode ser vazia.")
	private String senha;

	public JwtLogin() {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [usuario=" + usuario + ", senha=" + senha + "]";
	}

}
