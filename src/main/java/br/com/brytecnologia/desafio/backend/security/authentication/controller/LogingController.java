package br.com.brytecnologia.desafio.backend.security.authentication.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brytecnologia.desafio.backend.security.authentication.JwtLogin;
import br.com.brytecnologia.desafio.backend.security.authentication.JwtToken;
import br.com.brytecnologia.desafio.backend.security.authentication.JwtTokenUtil;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LogingController {

	private final Logger logger = LoggerFactory.getLogger(LogingController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Gera e retorna um novo token JWT.
	 * 
	 * @param login
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public ResponseEntity<JwtToken> gerarTokenJwt(@Valid @RequestBody JwtLogin login, BindingResult result)
			throws AuthenticationException {

		logger.info("Gerando token para o usuario {}.", login.getUsuario());
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getSenha()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsuario());

			return ResponseEntity.accepted().body(new JwtToken(jwtTokenUtil.obterToken(userDetails)));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

	}

}
