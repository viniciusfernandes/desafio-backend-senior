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

		Usuario admin = new Usuario();
		admin.setUsuario("admin");
		admin.setPerfil(PerfilEnum.ROLE_ADMIN);
		admin.setSenha(SenhaUtils.gerarBCrypt("adminpassword"));
		usuarioService.inserir(admin);

		Usuario readonly = new Usuario();
		readonly.setUsuario("readonly");
		readonly.setPerfil(PerfilEnum.ROLE_USUARIO);
		readonly.setSenha(SenhaUtils.gerarBCrypt("readonlypassword"));
		usuarioService.inserir(readonly);

		usuarioService.findAll()
				.forEach(user -> logger.info("Usuario: " + user.getUsuario() + " senha: " + user.getSenha()));

	}
}