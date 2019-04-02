package br.com.brytecnologia.desafio.backend.security.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Login;
import br.com.brytecnologia.desafio.backend.security.authentication.enums.PerfilEnum;

public class JwtUserFactory {

	public static JwtUser gerarJwtUser(Login login) {
		return new JwtUser(login.getId(), login.getUsuario(), login.getSenha(),
				getGrantedAuthorities(login.getPerfil()));
	}

	private static List<GrantedAuthority> getGrantedAuthorities(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}

	private JwtUserFactory() {
	}
}
