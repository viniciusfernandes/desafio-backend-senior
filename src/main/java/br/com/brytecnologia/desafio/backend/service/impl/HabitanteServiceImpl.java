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
	public String save(Habitante habitante) throws BusinessException {
		if (habitante == null) {
			throw new BusinessException("O habitante nao pode ser nulo.");
		} else if (!habitante.hasCodigo()) {
			throw new BusinessException("O codigo do habitante eh obrigatorio.");
		}
		return habitanteRepository.save(habitante);
	}

}
