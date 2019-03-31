package br.com.brytecnologia.desafio.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.repository.HabitanteRepository;
import br.com.brytecnologia.desafio.backend.service.HabitanteService;

@Service
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

}
