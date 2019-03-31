package br.com.brytecnologia.desafio.backend.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.brytecnologia.desafio.backend.entity.Endereco;
import br.com.brytecnologia.desafio.backend.repository.EnderecoRepository;
import br.com.brytecnologia.desafio.backend.service.EnderecoService;
import br.com.brytecnologia.desafio.backend.service.exception.BadFormatDataException;
import br.com.brytecnologia.desafio.backend.service.exception.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.exception.InvalidDataException;

@Service
@Transactional(readOnly = true)
public class EnderecoServiceImpl implements EnderecoService {
	private EnderecoRepository enderecoRepository;

	@Autowired
	public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}

	@Override
	public Endereco findByCodigoPostal(String codigoPostal) {

		/*
		 * Endereco endereco = new Endereco(); endereco.setBairro("Centro");
		 * endereco.setCodigoPostal(codigoPostal); endereco.setComplemento("Jr Rey");
		 * endereco.setLocalizacao("Diadema");
		 * endereco.setLogradouro("Avenida Visconde do rio branco");
		 * endereco.setNumero(987); endereco.setUf("SP"); return endereco;
		 * 
		 */
		Map<String, String> params = new HashMap<>();
		params.put("cep", codigoPostal);
		RestTemplate restTemplate = new RestTemplate();

		Endereco endereco = restTemplate.getForObject(URI_CEP, Endereco.class, params);
		endereco.setCodigoPostal(codigoPostal);
		return endereco;
	}

	@Override
	public Endereco populateEndereco(Endereco endereco) throws BlanckDataException, InvalidDataException {
		if (endereco == null || !endereco.hasCodigoPostal()) {
			throw new BlanckDataException("O codigo postal eh obrigatorio para o preenchimento do endereco");
		}

		Endereco end = findByCodigoPostal(endereco.getCodigoPostal());
		if (end == null) {
			throw new InvalidDataException("Nao existe endereco para o codigo postal " + endereco.getCodigoPostal());
		}

		endereco.setBairro(end.getBairro());
		endereco.setComplemento(end.getComplemento());
		endereco.setLocalizacao(end.getLocalizacao());
		endereco.setLogradouro(end.getLogradouro());
		endereco.setUf(end.getUf());

		return endereco;
	}

	@Override
	@Transactional(readOnly = false)
	public Endereco save(Endereco endereco) throws BlanckDataException, BadFormatDataException {
		if (endereco == null || !endereco.hasCodigoPostal()) {
			throw new BlanckDataException("O codigo postal do endereco nao pode estar em branco.");
		} else if (!isCodigoPostalValido(endereco.getCodigoPostal())) {
			throw new BadFormatDataException(
					"O codigo postal do endereco deve conter oito digitos numericos no padrao 99999999, mas foi enviado "
							+ endereco.getCodigoPostal());

		}
		return enderecoRepository.save(endereco);
	}

	public boolean isCodigoPostalValido(String codigoPostal) throws BadFormatDataException {
		return codigoPostal != null && codigoPostal.matches("\\d{8}");
	}

}
