package br.com.brytecnologia.desafio.backend;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.brytecnologia.desafio.backend.security.authentication.SenhaUtils;
import br.com.brytecnologia.desafio.backend.security.authentication.entity.Usuario;
import br.com.brytecnologia.desafio.backend.security.authentication.enums.PerfilEnum;
import br.com.brytecnologia.desafio.backend.security.authentication.service.UsuarioService;

@Component
public class DesafioBackendSeniorStartupRunner implements CommandLineRunner {

	private UsuarioService usuarioService;

	@Autowired
	public DesafioBackendSeniorStartupRunner(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void run(String... args) throws Exception {

		usuarioService.deleteAll();

		Usuario usuario = new Usuario();
		usuario.setEmail("usuario@email.com");
		usuario.setPerfil(PerfilEnum.ROLE_USUARIO);
		usuario.setSenha(SenhaUtils.gerarBCrypt("123456"));
		usuarioService.inserir(usuario);

		Usuario admin = new Usuario();
		admin.setEmail("admin@email.com");
		admin.setPerfil(PerfilEnum.ROLE_ADMIN);
		admin.setSenha(SenhaUtils.gerarBCrypt("123456"));
		usuarioService.inserir(admin);

	}
}