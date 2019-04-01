package br.com.brytecnologia.desafio.backend.security.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.brytecnologia.desafio.backend.security.authentication.entity.Login;

@Transactional(readOnly = true)
public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByUsuario(String usuario);

}
