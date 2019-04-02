package br.com.brytecnologia.desafio.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.brytecnologia.desafio.backend.entity.Habitante;

/**
 * Classe responsavel pelo acesso direto aos dados do sistema.
 * 
 * @author vinic
 *
 */
@Repository
public class HabitanteRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Deleta o habitante cadastrado no sistema e seus respectivos enderecos atraves
	 * de seu codigo
	 * 
	 * @param codigo chave primaria do habitante
	 */
	public void deleteByCodigo(String codigo) {
		// Removendo primeiramente os enderecos para que nao haja registros orfaos.
		em.createQuery("delete from Endereco e where e.habitante.codigo =:codigo").setParameter("codigo", codigo)
				.executeUpdate();
		em.createQuery("delete from Habitante h where h.codigo =:codigo").setParameter("codigo", codigo)
				.executeUpdate();
	}

	/**
	 * Pesquisa todos os habitantes do sistema
	 * 
	 * @return lista contendo todos os habitantes do sistema
	 */
	public List<Habitante> findAll() {
		// Executando um distinct pois o join fetch do hibernate traz registros
		// duplicados por conta de sua estrategia de implementacao.
		// O left joi eh necessario para recuperar os habitantes sem endereco.
		return em.createQuery("select distinct h from Habitante h left join fetch h.enderecos", Habitante.class)
				.getResultList();
	}

	/**
	 * Pesquisa o habitantes do sistema associado ao codigo
	 * 
	 * @param codigo chave primaria dos registros dos habitantes
	 * @return retorna o habitante associado ao codigo
	 */
	public Habitante findByCodigo(String codigo) {
		try {
			// O left joi eh necessario para recuperar os habitantes sem endereco.
			return em.createQuery("select h from Habitante h left join fetch h.enderecos where h.codigo=:codigo",
					Habitante.class).setParameter("codigo", codigo).getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}

	/**
	 * Verifica se o habitante ja existe no sistema
	 * 
	 * @param codigo chave primaria do habitante
	 * @return retorna true se o habitante ja esta cadastrado no sistema, e false no
	 *         caso contrario
	 */
	public boolean isCodigoExistente(String codigo) {
		return em.createQuery("select h.codigo from Habitante h where h.codigo=:codigo", String.class)
				.setParameter("codigo", codigo).getResultList().size() > 0;
	}

	/**
	 * Insere os dados do habitante no sistema
	 * 
	 * @param habitante dados do habitante
	 * @return retorna o habitante inserido no sistema.
	 */
	public Habitante save(Habitante habitante) {
		// Realizando um merge pois os IDs dos habitantes sao enviados pela aplicacao
		// que consome esse servico
		return em.merge(habitante);
	}
}
