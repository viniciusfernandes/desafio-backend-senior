package br.com.brytecnologia.desafio.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brytecnologia.desafio.backend.entity.Endereco;
import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.repository.HabitanteRepository;
import br.com.brytecnologia.desafio.backend.service.EnderecoService;
import br.com.brytecnologia.desafio.backend.service.HabitanteService;

@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class HabitanteServiceImpl implements HabitanteService {

	private HabitanteRepository habitanteRepository;

	private EnderecoService enderecoService;

	@Autowired
	public HabitanteServiceImpl(EnderecoService enderecoService, HabitanteRepository habitanteRepository) {
		this.enderecoService = enderecoService;
		this.habitanteRepository = habitanteRepository;
	}

	@Override
	public List<Habitante> findAll() {
		return habitanteRepository.findAll();
	}

	@Override
	public Habitante findByCodigo(String codigo) {
		return habitanteRepository.findByCodigo(codigo);
	}

	@Override
	@Transactional(readOnly = false)
	public Habitante save(Habitante habitante) throws BlanckDataException, ConflictDataException, InvalidDataException {
		if (habitante == null) {
			throw new BlanckDataException("O habitante nao pode ser nulo.");
		} else if (!habitante.hasCodigo()) {
			throw new BlanckDataException("O codigo do habitante eh obrigatorio.");
		} else if (isCodigoExistente(habitante.getCodigo())) {
			throw new ConflictDataException("O codigo do habitante ja esta cadastrado no sistema.");
		}

		if (habitante.hasEndereco()) {
			for (Endereco endereco : habitante.getEnderecos()) {
				enderecoService.populateEndereco(endereco);
			}
		}

		habitanteRepository.save(habitante);
		return habitante;
	}

	@Override
	public boolean isCodigoExistente(String codigo) {
		if (codigo == null || codigo.trim().length() <= 0) {
			return false;
		}
		return habitanteRepository.isCodigoExistente(codigo);
	}

}
