package br.com.brytecnologia.desafio.backend.service;

import java.util.List;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.service.impl.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.impl.ConflictDataException;
import br.com.brytecnologia.desafio.backend.service.impl.InvalidDataException;

public interface HabitanteService {
	List<Habitante> findAll();

	Habitante findByCodigo(String codigo);

	Habitante save(Habitante habitante) throws BlanckDataException, ConflictDataException, InvalidDataException;

	boolean isCodigoExistente(String codigo);
}
