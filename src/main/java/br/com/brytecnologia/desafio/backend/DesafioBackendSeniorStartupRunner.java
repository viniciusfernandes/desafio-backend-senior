package br.com.brytecnologia.desafio.backend;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.brytecnologia.desafio.backend.security.authentication.SenhaUtils;
import br.com.brytecnologia.desafio.backend.security.authentication.entity.Login;
import br.com.brytecnologia.desafio.backend.security.authentication.enums.PerfilEnum;
import br.com.brytecnologia.desafio.backend.security.authentication.service.LoginService;

@Component
public class DesafioBackendSeniorStartupRunner implements CommandLineRunner {

	private LoginService loginService;

	@Autowired
	public DesafioBackendSeniorStartupRunner(LoginService loginService) {
		this.loginService = loginService;
	}

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void run(String... args) throws Exception {

		loginService.deleteAll();

		Login admin = new Login();
		admin.setUsuario("admin");
		admin.setPerfil(PerfilEnum.ROLE_ADMIN);
		admin.setSenha(SenhaUtils.gerarBCrypt("adminpassword"));
		loginService.save(admin);

		Login readonly = new Login();
		readonly.setUsuario("readonly");
		readonly.setPerfil(PerfilEnum.ROLE_USUARIO);
		readonly.setSenha(SenhaUtils.gerarBCrypt("readonlypassword"));
		loginService.save(readonly);

		loginService.findAll()
				.forEach(login -> logger.info("Usuario cadastrado no STARTUP do sistema: " + login.getUsuario()));

	}
}