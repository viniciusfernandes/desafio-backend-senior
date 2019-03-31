package br.com.brytecnologia.desafio.backend.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.brytecnologia.desafio.backend.entity.Endereco;
import br.com.brytecnologia.desafio.backend.service.EnderecoService;

@Service
@Transactional(readOnly = true)
public class EnderecoServiceImpl implements EnderecoService {

	@Override
	public Endereco findByCodigoPostal(String codigoPostal) {

		Map<String, String> params = new HashMap<>();
		params.put("cep", codigoPostal);
		RestTemplate restTemplate = new RestTemplate();

		Endereco endereco = restTemplate.getForObject(URI_CEP, Endereco.class, params);
		endereco.setCodigoPostal(codigoPostal);
		return endereco;
	}

	@Override
	public Endereco populateEndereco(Endereco endereco) throws ConflictDataException, BlanckDataException {
		if (endereco == null || !endereco.hasCodigoPostal()) {
			throw new BlanckDataException("O codigo postal eh obrigatorio para o preenchimento do endereco");
		}

		Endereco end = findByCodigoPostal(endereco.getCodigoPostal());
		if (end == null) {
			throw new ConflictDataException("Nao existe endereco para o codigo postal " + endereco.getCodigoPostal());
		}

		endereco.setBairro(end.getBairro());
		endereco.setComplemento(end.getComplemento());
		endereco.setLocalizacao(end.getLocalizacao());
		endereco.setLogradouro(end.getLogradouro());
		endereco.setUf(end.getUf());

		return endereco;
	}

}
