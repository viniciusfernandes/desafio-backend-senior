package br.com.brytecnologia.desafio.backend.service;

import java.util.List;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.service.exception.BadFormatDataException;
import br.com.brytecnologia.desafio.backend.service.exception.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.exception.ConflictDataException;
import br.com.brytecnologia.desafio.backend.service.exception.InvalidDataException;
import br.com.brytecnologia.desafio.backend.service.exception.NoDataException;

/**
 * Interface que define todas as regras de negocio associadas a inclusao,
 * recuperacao, alteracao e remocao dos dados dos habitantes no sistema.
 * 
 * @author vinic
 *
 */
public interface HabitanteService {

	/**
	 * Deleta o habitante cadastrado no sistema e seus respectivos enderecos atraves
	 * de seu codigo
	 * 
	 * @param codigo chave primaria do habitante
	 */
	void deleteByCodigo(String codigo) throws InvalidDataException;

	/**
	 * Pesquisa todos os habitantes do sistema.
	 * 
	 * @return lista contendo todos os habitantes do sistema.
	 */
	List<Habitante> findAll();

	/**
	 * 
	 * Pesquisa o habitantes do sistema associado ao codigo.
	 * 
	 * @param codigo chave primaria dos registros dos habitantes.
	 * @return retorna o habitante associado ao codigo.
	 * @throws NoDataException excecao lancada no caso de nao existencia de um
	 *                         habitante com o codigo.
	 */
	Habitante findByCodigo(String codigo) throws NoDataException;

	/**
	 * Verifica se o habitante ja existe no sistema
	 * 
	 * @param codigo chave primaria do habitante
	 * @return retorna true se o habitante ja esta cadastrado no sistema, e false no
	 *         caso contrario
	 */
	boolean isCodigoExistente(String codigo);

	/**
	 * Metodo que executa todas as validacoes para a inclusao dos dados de um
	 * habitante no sistema, bem como seu endereco.
	 * 
	 * @param habitante dados do habitante.
	 * @return retorna o habitante incluido no sistema e os dados adicionais do
	 *         endereco, logradouro, codigo postal, complemento e UF.
	 * @throws BlanckDataException    excecao lancada quando os dados obrigatorios
	 *                                de um habitante estao em branco.
	 * @throws ConflictDataException  excecao lancada quando ja existe um habitante
	 *                                com o mesmo codigo no sistema.
	 * @throws InvalidDataException   excecao lancada quando o codigo postal do
	 *                                endereco do habitante esta invalido.
	 * @throws BadFormatDataException excecao lancada quando o codigo postal do
	 *                                endereco do habitante nao esta dentro dos
	 *                                padroes.
	 * @throws NoDataException        excecao lancada quando o endereco do habitante
	 *                                nao existe.
	 */
	Habitante save(Habitante habitante) throws BlanckDataException, ConflictDataException, InvalidDataException,
			BadFormatDataException, NoDataException;

	/**
	 * Metodo que executa todas as validacoes para a alteracao dos dados de um
	 * habitante no sistema. Os enderecos enviados sao adicionados os outros
	 * enderecos ja cadastrados no sistema.
	 * 
	 * @param habitante dados do habitante.
	 * @return retorna o habitante incluido no sistema e os dados adicionais do
	 *         endereco, logradouro, codigo postal, complemento e UF.
	 * @throws BlanckDataException    excecao lancada quando os dados obrigatorios
	 *                                de um habitante estao em branco.
	 * @throws InvalidDataException   excecao lancada quando o codigo postal do
	 *                                endereco do habitante esta invalido.
	 * @throws BadFormatDataException excecao lancada quando o codigo postal do
	 *                                endereco do habitante nao esta dentro dos
	 *                                padroes.
	 * @throws NoDataException        excecao lancada quando o endereco do habitante
	 *                                nao existe.
	 */
	Habitante update(Habitante habitante)
			throws BlanckDataException, BadFormatDataException, InvalidDataException, NoDataException;
}
