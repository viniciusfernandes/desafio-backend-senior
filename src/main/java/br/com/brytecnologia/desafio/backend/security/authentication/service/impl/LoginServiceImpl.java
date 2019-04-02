package br.com.brytecnologia.desafio.backend.security.authentication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Login;
import br.com.brytecnologia.desafio.backend.security.authentication.repository.LoginRepository;
import br.com.brytecnologia.desafio.backend.security.authentication.service.LoginService;

@Service
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Transactional(readOnly = false)
	@Override
	public void deleteAll() {
		loginRepository.deleteAll();
	}

	@Override
	public List<Login> findAll() {
		return loginRepository.findAll();
	}

	@Override
	public Login findByUsuario(String usuario) {
		if (usuario == null || usuario.trim().isEmpty()) {
			return null;
		}
		return loginRepository.findByUsuario(usuario);
	}

	@Transactional(readOnly = false)
	@Override
	public Long save(Login login) {
		return loginRepository.save(login).getId();
	}

}
