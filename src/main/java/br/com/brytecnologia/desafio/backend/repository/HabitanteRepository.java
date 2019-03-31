package br.com.brytecnologia.desafio.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.brytecnologia.desafio.backend.entity.Habitante;

@Repository
public class HabitanteRepository {
	@PersistenceContext
	private EntityManager em;

	public Habitante findByCodigo(String codigo) {
		try {
			return em.createQuery("select h from Habitante h where h.codigo=:codigo", Habitante.class)
					.setParameter("codigo", codigo).getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}

	public List<Habitante> findAll() {
		return em.createQuery("select * from Habitante ", Habitante.class).getResultList();
	}
}