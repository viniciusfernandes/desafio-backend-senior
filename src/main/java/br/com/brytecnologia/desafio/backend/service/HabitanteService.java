package br.com.brytecnologia.desafio.backend.service;

import java.util.List;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.service.exception.BadFormatDataException;
import br.com.brytecnologia.desafio.backend.service.exception.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.exception.ConflictDataException;
import br.com.brytecnologia.desafio.backend.service.exception.InvalidDataException;
import br.com.brytecnologia.desafio.backend.service.exception.NoDataException;

public interface HabitanteService {

	/**
	 * Pesquisa todos os habitantes do sistema
	 * 
	 * @return lista contendo todos os habitantes do sistema
	 */
	List<Habitante> findAll();

	/**
	 * 
	 * Pesquisa o habitantes do sistema associado ao codigo
	 * 
	 * @param codigo chave primaria dos registros dos habitantes
	 * @return retorna o habitante associado ao codigo
	 * @throws NoDataException excecao lancada no caso de nao existencia de um
	 *                         habitante com o codigo.
	 */
	Habitante findByCodigo(String codigo) throws NoDataException;

	/**
	 * Metodo que executa todas as validacoes para a inclusao de um habitante no sistema, bem como seu endereco.
	 * 
	 * @param habitante
	 * @return retorna o habitante incluido no sistema e os dados adicionais do endereco, logradouro, codigo postal, complemento e UF.
	 * @throws BlanckDataException 
	 * @throws ConflictDataException excecao lancada quando ja existe um habitante com o mesmo codigo no sistema
	 * @throws InvalidDataException
	 * @throws BadFormatDataException
	 */
	Habitante save(Habitante habitante)
			throws BlanckDataException, ConflictDataException, InvalidDataException, BadFormatDataException;

	boolean isCodigoExistente(String codigo);

	void deleteByCodigo(String codigo) throws InvalidDataException;

	Habitante update(Habitante habitante)
			throws BlanckDataException, BadFormatDataException, InvalidDataException, NoDataException;
}
