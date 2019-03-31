package br.com.brytecnologia.desafio.backend.service;

import java.util.List;

import br.com.brytecnologia.desafio.backend.entity.Habitante;

public interface HabitanteService {
	List<Habitante> findAll();

	Habitante findByCodigo(String codigo);
}
