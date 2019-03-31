package br.com.brytecnologia.desafio.backend.service;

import java.util.List;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.service.impl.BusinessException;

public interface HabitanteService {
	List<Habitante> findAll();

	Habitante findByCodigo(String codigo);

	String save(Habitante habitante) throws BusinessException;
}
