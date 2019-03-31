package br.com.brytecnologia.desafio.backend.service;

import br.com.brytecnologia.desafio.backend.entity.Endereco;
import br.com.brytecnologia.desafio.backend.service.exception.BadFormatDataException;
import br.com.brytecnologia.desafio.backend.service.exception.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.exception.InvalidDataException;

public interface EnderecoService {
	public static final String URI_CEP = "http://viacep.com.br/ws/{cep}/json/";

	Endereco findByCodigoPostal(String codigoPostal);

	Endereco populateEndereco(Endereco endereco) throws InvalidDataException, BlanckDataException;

	Integer save(Endereco endereco) throws BlanckDataException, BadFormatDataException;
}
