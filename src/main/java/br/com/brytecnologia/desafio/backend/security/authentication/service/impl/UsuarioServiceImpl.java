package br.com.brytecnologia.desafio.backend.security.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Usuario;
import br.com.brytecnologia.desafio.backend.security.authentication.repository.UsuarioDAO;
import br.com.brytecnologia.desafio.backend.security.authentication.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Transactional(readOnly = true)
	@Override
	public Usuario buscarPorEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			return null;
		}
		return usuarioDAO.findByEmail(email);
	}

	@Transactional(readOnly = false)
	@Override
	public Long inserir(Usuario usuario) {
		return usuarioDAO.save(usuario).getId();
	}
	

	@Transactional(readOnly = false)
	@Override
	public void deleteAll() {
		usuarioDAO.deleteAll();
	}

}
