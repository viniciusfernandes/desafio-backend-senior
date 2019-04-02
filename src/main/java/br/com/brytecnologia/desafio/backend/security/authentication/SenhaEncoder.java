package br.com.brytecnologia.desafio.backend.security.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SenhaEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence senha) {
		if (senha == null || senha.length() <= 0) {
			throw new IllegalArgumentException("A senha deve ser preenchida para realizar a encriptacao.");
		}

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Falha na encriptacao da senha do usuario.", e);
		}
		md.update(senha.toString().getBytes());
		byte[] digest = md.digest();
		return DatatypeConverter.printHexBinary(digest).toLowerCase();
	}

	@Override
	public boolean matches(CharSequence senha, String senhaEncriptada) {
		return senha != null && senhaEncriptada != null && senha.length() > 0 && senhaEncriptada.length() > 0
				&& encode(senha.toString()).equals(senhaEncriptada);
	}

}
