package br.com.brytecnologia.desafio.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brytecnologia.desafio.backend.entity.Endereco;
import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.repository.HabitanteRepository;
import br.com.brytecnologia.desafio.backend.service.EnderecoService;
import br.com.brytecnologia.desafio.backend.service.HabitanteService;
import br.com.brytecnologia.desafio.backend.service.exception.BadFormatDataException;
import br.com.brytecnologia.desafio.backend.service.exception.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.exception.ConflictDataException;
import br.com.brytecnologia.desafio.backend.service.exception.InvalidDataException;
import br.com.brytecnologia.desafio.backend.service.exception.NoDataException;

/**
 * Classe responsavel pela implementacao de todas as regras de negocio
 * associadas a inclusao, recuperacao, alteracao e remocao dos dados dos
 * habitantes no sistema.
 * 
 * @author vinic
 *
 */
@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class HabitanteServiceImpl implements HabitanteService {
	private EnderecoService enderecoService;
	private HabitanteRepository habitanteRepository;

	/*
	 * Uma boa pratica eh efetuar a injecao de dependencias via construtor ao inves
	 * de injecao no atributo. Assim podemos identificar os pontos de dependencia
	 * ciclica.
	 */
	@Autowired
	public HabitanteServiceImpl(EnderecoService enderecoService, HabitanteRepository habitanteRepository) {
		this.enderecoService = enderecoService;
		this.habitanteRepository = habitanteRepository;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteByCodigo(String codigo) throws InvalidDataException {
		if (codigo == null || !isCodigoExistente(codigo)) {
			throw new InvalidDataException("Nao existe habitante com codigo " + codigo);
		}
		habitanteRepository.deleteByCodigo(codigo);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<Habitante> findAll() {
		return habitanteRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws NoDataException
	 * 
	 */
	@Override
	public Habitante findByCodigo(String codigo) throws NoDataException {
		Habitante habitante = habitanteRepository.findByCodigo(codigo);
		if (habitante == null) {
			throw new NoDataException("Nao existe habitante com o codigo " + codigo);
		}
		return habitante;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public boolean isCodigoExistente(String codigo) {
		if (codigo == null || codigo.trim().length() <= 0) {
			return false;
		}
		return habitanteRepository.isCodigoExistente(codigo);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws NoDataException
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public Habitante save(Habitante habitante) throws BlanckDataException, ConflictDataException, InvalidDataException,
			BadFormatDataException, NoDataException {

		validate(habitante);
		if (isCodigoExistente(habitante.getCodigo())) {
			throw new ConflictDataException("O codigo do habitante ja esta cadastrado no sistema.");
		}
		return saveOrUpdate(habitante);
	}

	private Habitante saveOrUpdate(Habitante habitante)
			throws BlanckDataException, BadFormatDataException, InvalidDataException, NoDataException {
		// Primeiro inserindo o habitante para depois inserir suas dependecias.
		habitanteRepository.save(habitante);
		if (habitante.hasEndereco()) {
			List<Endereco> enderecos = new ArrayList<>();
			enderecos.addAll(habitante.getEnderecos());
			habitante.clearEnderecos();
			for (Endereco endereco : enderecos) {
				endereco.setHabitante(habitante);
				habitante.addEndereco(enderecoService.save(endereco));
			}
		}

		return habitante;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public Habitante update(Habitante habitante)
			throws BlanckDataException, BadFormatDataException, InvalidDataException, NoDataException {
		validate(habitante);
		if (!isCodigoExistente(habitante.getCodigo())) {
			throw new NoDataException("O codigo do habitante nao existe no sistema.");
		}
		return saveOrUpdate(habitante);
	}

	private void validate(Habitante habitante) throws BlanckDataException {
		if (habitante == null) {
			throw new BlanckDataException("O habitante nao pode ser nulo.");
		} else if (!habitante.hasCodigo()) {
			throw new BlanckDataException("O codigo do habitante eh obrigatorio.");
		}
	}

}
