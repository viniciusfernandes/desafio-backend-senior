package br.com.brytecnologia.desafio.backend.security.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.brytecnologia.desafio.backend.security.authentication.JwtUserFactory;
import br.com.brytecnologia.desafio.backend.security.authentication.entity.Usuario;
import br.com.brytecnologia.desafio.backend.security.authentication.service.UsuarioService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null || username.trim().isEmpty()) {
			throw new UsernameNotFoundException("Usuário esta em branco.");
		}

		Usuario u = usuarioService.findByUsuario(username);
		if (u != null) {
			return JwtUserFactory.gerarJwtUser(u);
		}

		throw new UsernameNotFoundException("Usuário \"" + username + "\" não encontrado.");
	}
}
