package br.com.brytecnologia.desafio.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.service.exception.InvalidDataException;

@Repository
public class HabitanteRepository {

	@PersistenceContext
	private EntityManager em;

	public Habitante findByCodigo(String codigo) {
		try {
			return em.createQuery("select h from Habitante h join fetch h.enderecos where h.codigo=:codigo", Habitante.class)
					.setParameter("codigo", codigo).getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}

	public List<Habitante> findAll() {
		return em.createQuery("select h from Habitante h join fetch h.enderecos", Habitante.class).getResultList();
	}

	public Habitante save(Habitante habitante) {
		return em.merge(habitante);
	}

	public boolean isCodigoExistente(String codigo) {
		return em.createQuery("select h.codigo from Habitante h where h.codigo=:codigo", String.class)
				.setParameter("codigo", codigo).getResultList().size() > 0;
	}

	public void deleteByCodigo(String codigo) throws InvalidDataException {
		em.createQuery("delete from Endereco e where e.habitante.codigo =:codigo").setParameter("codigo", codigo)
				.executeUpdate();
		em.createQuery("delete from Habitante h where h.codigo =:codigo").setParameter("codigo", codigo)
				.executeUpdate();
	}
}
