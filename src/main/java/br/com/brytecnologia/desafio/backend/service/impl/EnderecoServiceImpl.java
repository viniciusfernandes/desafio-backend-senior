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
import br.com.brytecnologia.desafio.backend.service.exception.ClientServiceException;
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
		Map<String, String> params = new HashMap<>();
		params.put("cep", codigoPostal);
		RestTemplate restTemplate = new RestTemplate();
		Endereco endereco = null;
		try {
			endereco = restTemplate.getForObject(URI_CEP, Endereco.class, params);
		} catch (Exception e) {
			throw new ClientServiceException("Falha na comunicacao com o servico de CEP", e);
		}
		if (!endereco.hasLogradouro()) {
			return null;
		}
		endereco.setCodigoPostal(codigoPostal);
		return endereco;
	}

	@Override
	public Endereco populateEndereco(Endereco enderecoHabitante) throws BlanckDataException, InvalidDataException {
		if (enderecoHabitante == null || !enderecoHabitante.hasCodigoPostal()) {
			throw new BlanckDataException("O codigo postal eh obrigatorio para o preenchimento do endereco");
		} else if (!isCodigoPostalValido(enderecoHabitante.getCodigoPostal())) {
			throw new InvalidDataException(
					"O codigo postal do endereco deve conter oito digitos numericos no padrao 99999999, mas foi enviado "
							+ enderecoHabitante.getCodigoPostal());

		}

		Endereco endereco = findByCodigoPostal(enderecoHabitante.getCodigoPostal());
		if (endereco == null) {
			throw new InvalidDataException(
					"Nao existe endereco para o codigo postal " + enderecoHabitante.getCodigoPostal());
		}

		enderecoHabitante.setBairro(endereco.getBairro());
		enderecoHabitante.setComplemento(endereco.getComplemento());
		enderecoHabitante.setLocalidade(endereco.getLocalidade());
		enderecoHabitante.setLogradouro(endereco.getLogradouro());
		enderecoHabitante.setUf(endereco.getUf());

		return enderecoHabitante;
	}

	@Override
	@Transactional(readOnly = false)
	public Endereco save(Endereco endereco) throws BlanckDataException, BadFormatDataException, InvalidDataException {

		endereco = populateEndereco(endereco);

		if (endereco == null || !endereco.hasCodigoPostal()) {
			throw new BlanckDataException("O codigo postal do endereco nao pode estar em branco.");
		} else if (!isCodigoPostalValido(endereco.getCodigoPostal())) {
			throw new BadFormatDataException(
					"O codigo postal do endereco deve conter oito digitos numericos no padrao 99999999, mas foi enviado "
							+ endereco.getCodigoPostal());

		}
		return enderecoRepository.save(endereco);
	}

	public boolean isCodigoPostalValido(String codigoPostal) {
		return codigoPostal != null && codigoPostal.matches("\\d{8}");
	}

}
