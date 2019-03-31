package br.com.brytecnologia.desafio.backend.service;

import br.com.brytecnologia.desafio.backend.entity.Endereco;
import br.com.brytecnologia.desafio.backend.service.impl.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.impl.ConflictDataException;
import br.com.brytecnologia.desafio.backend.service.impl.InvalidDataException;

public interface EnderecoService {
	public static final String URI_CEP = "http://viacep.com.br/ws/{cep}/json/";

	Endereco findByCodigoPostal(String codigoPostal);

	Endereco populateEndereco(Endereco endereco) throws InvalidDataException, BlanckDataException, ConflictDataException;
}
