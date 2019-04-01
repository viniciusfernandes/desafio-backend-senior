package br.com.brytecnologia.desafio.backend.security.authentication.service;

import java.util.List;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Login;

public interface LoginService {

	Login findByUsername(String login);

	Long save(Login usuario);

	void deleteAll();

	List<Login> findAll();

}
