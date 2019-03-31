package br.com.brytecnologia.desafio.backend.service;

import java.util.List;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.service.exception.BadFormatDataException;
import br.com.brytecnologia.desafio.backend.service.exception.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.exception.ConflictDataException;
import br.com.brytecnologia.desafio.backend.service.exception.InvalidDataException;

public interface HabitanteService {
	List<Habitante> findAll();

	Habitante findByCodigo(String codigo);

	Habitante save(Habitante habitante) throws BlanckDataException, ConflictDataException, InvalidDataException, BadFormatDataException;

	boolean isCodigoExistente(String codigo);
}
