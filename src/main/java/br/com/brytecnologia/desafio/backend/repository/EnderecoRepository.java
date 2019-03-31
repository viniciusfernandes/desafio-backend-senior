package br.com.brytecnologia.desafio.backend.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.brytecnologia.desafio.backend.entity.Endereco;

@Repository
public class EnderecoRepository {
	@PersistenceContext
	private EntityManager em;

	public Endereco save(Endereco endereco) {
		em.persist(endereco);
		return endereco;
	}
}
