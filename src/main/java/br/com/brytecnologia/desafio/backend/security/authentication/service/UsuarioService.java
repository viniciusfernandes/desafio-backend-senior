package br.com.brytecnologia.desafio.backend.security.authentication.service;

import java.util.List;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Usuario;

public interface UsuarioService {

	Usuario findByUsuario(String usuario);

	Long inserir(Usuario usuario);

	void deleteAll();

	List<Usuario> findAll();

}
