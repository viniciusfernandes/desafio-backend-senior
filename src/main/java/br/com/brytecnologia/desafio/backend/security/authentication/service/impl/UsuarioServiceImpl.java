package br.com.brytecnologia.desafio.backend.security.authentication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Usuario;
import br.com.brytecnologia.desafio.backend.security.authentication.repository.UsuarioDAO;
import br.com.brytecnologia.desafio.backend.security.authentication.service.UsuarioService;

@Service
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	
	@Override
	public Usuario findByUsuario(String usuario) {
		if (usuario == null || usuario.trim().isEmpty()) {
			return null;
		}
		return usuarioDAO.findByUsuario(usuario);
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
	
	@Override
	public List<Usuario> findAll(){
		return usuarioDAO.findAll();
	}

}
