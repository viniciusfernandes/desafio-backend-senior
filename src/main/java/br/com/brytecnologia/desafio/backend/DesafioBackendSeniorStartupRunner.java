package br.com.brytecnologia.desafio.backend;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Login;
import br.com.brytecnologia.desafio.backend.security.authentication.enums.PerfilEnum;
import br.com.brytecnologia.desafio.backend.security.authentication.service.LoginService;

/**
 * Classe responsavel por efetuar a leitura e o carregamento das informacoes dos
 * usuarios na inicializacao da aplicacao.
 * 
 * @author vinic
 *
 */
@Component
public class DesafioBackendSeniorStartupRunner implements CommandLineRunner {

	private LoginService loginService;

	@Autowired
	public DesafioBackendSeniorStartupRunner(LoginService loginService) {
		this.loginService = loginService;
	}

	private Logger logger = LoggerFactory.getLogger(DesafioBackendSeniorStartupRunner.class);

	@Override
	public void run(String... args) throws Exception {
		loginService.deleteAll();

		// Efetuando a leitura do arquivo dos usuarios para posteriormente inserir os
		// dados no DB. Essa estrategia permite que, ao se remover o arquivo
		// usuarios.properties, o sistema nao necessite de qualquer outra alteracao.
		Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("usuarios.properties"));

		Login admin = new Login();
		admin.setUsuario("admin");
		admin.setPerfil(PerfilEnum.ROLE_ADMIN);
		admin.setSenha(prop.getProperty("admin"));
		loginService.save(admin);

		Login readonly = new Login();
		readonly.setUsuario("readonly");
		readonly.setPerfil(PerfilEnum.ROLE_READONLY);
		readonly.setSenha(prop.getProperty("readonly"));
		loginService.save(readonly);

		loginService.findAll()
				.forEach(login -> logger.info("Usuario cadastrado no STARTUP do sistema: " + login.getUsuario()));

	}
}