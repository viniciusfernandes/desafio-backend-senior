package br.com.brytecnologia.desafio.backend.security.authentication.service;

import java.util.List;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Login;

public interface LoginService {

	void deleteAll();

	List<Login> findAll();

	Login findByUsuario(String usuario);

	Long save(Login usuario);

}
