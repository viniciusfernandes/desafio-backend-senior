package br.com.brytecnologia.desafio.backend.security.authentication.service;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Usuario;

public interface UsuarioService {

	Usuario buscarPorEmail(String email);

	Long inserir(Usuario usuario);

	void deleteAll();

}
