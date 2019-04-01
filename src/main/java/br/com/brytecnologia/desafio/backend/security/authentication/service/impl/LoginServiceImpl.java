package br.com.brytecnologia.desafio.backend.security.authentication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Login;
import br.com.brytecnologia.desafio.backend.security.authentication.repository.LoginRepository;
import br.com.brytecnologia.desafio.backend.security.authentication.service.LoginService;

@Service
@Transactional(readOnly = false)
public class LoginServiceImpl implements LoginService {

	private LoginRepository loginRepository;

	@Autowired
	public LoginServiceImpl(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public List<Login> findAll() {
		return loginRepository.findAll();
	}

	@Override
	public Login findByUsername(String username) {
		if (username == null || username.trim().isEmpty()) {
			return null;
		}
		return loginRepository.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = false)
	public Long save(Login usuario) {
		return loginRepository.save(usuario).getId();
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteAll() {
		loginRepository.deleteAll();
	}

}
