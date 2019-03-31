package br.com.brytecnologia.desafio.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.repository.HabitanteRepository;
import br.com.brytecnologia.desafio.backend.service.HabitanteService;

@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class HabitanteServiceImpl implements HabitanteService {

	private HabitanteRepository habitanteRepository;

	@Autowired
	public HabitanteServiceImpl(HabitanteRepository habitanteRepository) {
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
	public Habitante save(Habitante habitante) throws BlanckDataException, InvalidDataException {
		if (habitante == null) {
			throw new BlanckDataException("O habitante nao pode ser nulo.");
		} else if (!habitante.hasCodigo()) {
			throw new BlanckDataException("O codigo do habitante eh obrigatorio.");
		} else if (isCodigoExistente(habitante.getCodigo())) {
			throw new InvalidDataException("O codigo do habitante ja esta cadastrado no sistema.");
		}
		return habitanteRepository.save(habitante);
	}

	@Override
	public boolean isCodigoExistente(String codigo) {
		if (codigo == null || codigo.trim().length() <= 0) {
			return false;
		}
		return habitanteRepository.isCodigoExistente(codigo);
	}

}
