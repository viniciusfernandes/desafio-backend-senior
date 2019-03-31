package br.com.brytecnologia.desafio.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.service.HabitanteService;
import br.com.brytecnologia.desafio.backend.service.exception.BadFormatDataException;
import br.com.brytecnologia.desafio.backend.service.exception.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.exception.ConflictDataException;
import br.com.brytecnologia.desafio.backend.service.exception.InvalidDataException;

@CrossOrigin
@RestController
@RequestMapping("habitantes")
public class HabitanteController {

	private HabitanteService habitanteService;

	@Autowired
	public HabitanteController(HabitanteService habitanteService) {
		this.habitanteService = habitanteService;
	}

	@GetMapping("")
	public ResponseEntity<List<Habitante>> findAll() {
		return new ResponseEntity<List<Habitante>>(habitanteService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Habitante> findByCodigo(@PathVariable String codigo) {
		Habitante habitante = habitanteService.findByCodigo(codigo);
		if (habitante != null) {
			return new ResponseEntity<Habitante>(habitante, HttpStatus.OK);
		} else {
			return new ResponseEntity<Habitante>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity<Habitante> save(@RequestBody Habitante habitante) throws BlanckDataException {
		try {
			return new ResponseEntity<Habitante>(habitanteService.save(habitante), HttpStatus.CREATED);
		} catch (ConflictDataException e) {
			return new ResponseEntity<Habitante>(HttpStatus.CONFLICT);
		} catch (BlanckDataException | BadFormatDataException | InvalidDataException e) {
			return new ResponseEntity<Habitante>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{codigo}")
	public String atualizarHabitante(Habitante habitante) {
		return null;
	}

	@DeleteMapping("/{codigo}")
	public String removerHabitante(String codigo) {
		return null;
	}
}
