package br.com.brytecnologia.desafio.backend.service;

import br.com.brytecnologia.desafio.backend.entity.Endereco;
import br.com.brytecnologia.desafio.backend.service.exception.BadFormatDataException;
import br.com.brytecnologia.desafio.backend.service.exception.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.exception.NoDataException;

/**
 * Interface que define todas as validacoes e regras de negocios aplicadas aos
 * dados dos enderecos do sistema.
 * 
 * @author vinic
 *
 */
public interface EnderecoService {
	public static final String URI_CEP = "http://viacep.com.br/ws/{cep}/json/";

	/**
	 * Metodo para recuperar o endereco pelo codigo postal.
	 * 
	 * @param codigoPostal codigo postal do endereco.
	 * @return retorna o endereco associado ao codigo postal.
	 */
	Endereco findByCodigoPostal(String codigoPostal);

	/**
	 * Metodo que popula o objeto endereco com os dados recuperados de uma consulta
	 * ao servico de CEP a partir de seu codigo postal.
	 * 
	 * @param endereco objeto para ser populado pelo servico de CEP
	 * @return
	 * @throws BlanckDataException excecao lancada no caso em que o codigo postal
	 *                             esteja em branco.
	 * @throws NoDataException     excecao lancada no caso em que o codigo postal
	 *                             nao esteja cadastrado no servico de CEP.
	 */
	Endereco populateEndereco(Endereco endereco) throws BlanckDataException, NoDataException;

	/**
	 * Metodo que executa as validacoes e insere o endereco no sistema juntamente
	 * com os dados de logradouro, complemento, UF, localidade e bairro, recuperados
	 * pelo servico de CEP.
	 * 
	 * @param endereco dados do endereco
	 * @return retorna o endereco inserido no sistema com os dados de logradouro,
	 *         complemento, UF, localidade e bairro, recuperados pelo servico de
	 *         CEP.
	 * @throws BlanckDataException    excecao lancada no caso em que o codigo postal
	 *                                esteja em branco.
	 * @throws BadFormatDataException excecao lancada no caso em que o codigo postal
	 *                                nao esteja nos padroes convencionados de 8
	 *                                digitos.
	 * @throws NoDataException        excecao lancada no caso em que o codigo postal
	 *                                nao esteja cadastrado no servico de CEP.
	 */
	Endereco save(Endereco endereco) throws BlanckDataException, BadFormatDataException, NoDataException;

	/**
	 * Metodo que verifica de um codigo postal esta dentro dos padroes
	 * convencionados de 8 digitos.
	 * 
	 * @param codigoPostal codigo postal do endereco.
	 * @return retorna true se o codigo postal esta dentro dos padroes ou false no
	 *         caso contrario.
	 */
	boolean isCodigoPostalValido(String codigoPostal);
}
