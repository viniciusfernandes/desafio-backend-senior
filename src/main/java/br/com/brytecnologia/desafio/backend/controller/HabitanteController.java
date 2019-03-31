package br.com.brytecnologia.desafio.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.service.HabitanteService;
import br.com.brytecnologia.desafio.backend.service.impl.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.impl.InvalidDataException;

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
	public ResponseEntity<Habitante> findByCodigo(String codigo) {
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
		} catch (BlanckDataException e) {
			return new ResponseEntity<Habitante>(HttpStatus.BAD_REQUEST);

		} catch (InvalidDataException e) {
			return new ResponseEntity<Habitante>(HttpStatus.CONFLICT);

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
