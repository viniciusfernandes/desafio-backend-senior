package br.com.brytecnologia.desafio.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brytecnologia.desafio.backend.dto.EnderecoDTO;
import br.com.brytecnologia.desafio.backend.dto.HabitanteDTO;
import br.com.brytecnologia.desafio.backend.entity.Endereco;
import br.com.brytecnologia.desafio.backend.entity.Habitante;
import br.com.brytecnologia.desafio.backend.service.HabitanteService;
import br.com.brytecnologia.desafio.backend.service.exception.BadFormatDataException;
import br.com.brytecnologia.desafio.backend.service.exception.BlanckDataException;
import br.com.brytecnologia.desafio.backend.service.exception.ClientServiceException;
import br.com.brytecnologia.desafio.backend.service.exception.ConflictDataException;
import br.com.brytecnologia.desafio.backend.service.exception.InvalidDataException;
import br.com.brytecnologia.desafio.backend.service.exception.NoDataException;
import br.com.brytecnologia.desafio.backend.utils.EntityUtils;

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
	@PreAuthorize("hasAnyRole('ADMIN', 'READONLY')")
	public ResponseEntity<List<HabitanteDTO>> findAll() {
		return new ResponseEntity<List<HabitanteDTO>>(convert(habitanteService.findAll()), HttpStatus.OK);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN', 'READONLY')")
	public ResponseEntity<HabitanteDTO> findByCodigo(@PathVariable String codigo) {
		try {
			HabitanteDTO dto = convert(habitanteService.findByCodigo(codigo));
			return new ResponseEntity<HabitanteDTO>(dto, HttpStatus.OK);
		} catch (NoDataException e) {
			return new ResponseEntity<HabitanteDTO>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<HabitanteDTO> save(@RequestBody HabitanteDTO habitanteDto) {
		try {

			HabitanteDTO dto = convert(habitanteService.save(convert(habitanteDto)));
			HttpHeaders headers = configHeaderLocation(dto);

			return new ResponseEntity<HabitanteDTO>(dto, headers, HttpStatus.CREATED);
		} catch (ConflictDataException e) {
			return new ResponseEntity<HabitanteDTO>(HttpStatus.CONFLICT);
		} catch (BlanckDataException | BadFormatDataException | InvalidDataException e) {
			return new ResponseEntity<HabitanteDTO>(HttpStatus.BAD_REQUEST);
		} catch (ClientServiceException e) {
			return new ResponseEntity<HabitanteDTO>(HttpStatus.SERVICE_UNAVAILABLE);
		}

	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<HabitanteDTO> update(@PathVariable String codigo, @RequestBody HabitanteDTO habitanteDto) {
		if (habitanteDto != null) {
			habitanteDto.setCodigo(codigo);
		}
		try {
			habitanteService.update(convert(habitanteDto));
			return new ResponseEntity<HabitanteDTO>(HttpStatus.NO_CONTENT);
		} catch (BlanckDataException | BadFormatDataException | InvalidDataException e) {
			return new ResponseEntity<HabitanteDTO>(HttpStatus.BAD_REQUEST);
		} catch (ClientServiceException e) {
			return new ResponseEntity<HabitanteDTO>(HttpStatus.SERVICE_UNAVAILABLE);
		} catch (NoDataException e) {
			return new ResponseEntity<HabitanteDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<HabitanteDTO> deleteByCodigo(@PathVariable String codigo) {
		try {
			habitanteService.deleteByCodigo(codigo);
			return new ResponseEntity<HabitanteDTO>(HttpStatus.NO_CONTENT);
		} catch (InvalidDataException e) {
			return new ResponseEntity<HabitanteDTO>(HttpStatus.NOT_FOUND);
		}
	}

	private HabitanteDTO convert(Habitante habitante) {
		HabitanteDTO habitanteDto = new HabitanteDTO();
		EntityUtils.copy(habitante, habitanteDto);

		if (habitante.hasEndereco()) {

			habitanteDto.clearEnderecos();
			EnderecoDTO endDto = null;
			for (Endereco end : habitante.getEnderecos()) {
				endDto = new EnderecoDTO();
				EntityUtils.copy(end, endDto);
				habitanteDto.addEndereco(endDto);
			}
		}

		return habitanteDto;
	}

	private List<HabitanteDTO> convert(List<Habitante> habitantes) {
		List<HabitanteDTO> dtos = new ArrayList<>(habitantes.size());
		habitantes.forEach(hab -> dtos.add(convert(hab)));
		return dtos;
	}

	private Habitante convert(HabitanteDTO habitanteDto) {
		Habitante habitante = new Habitante();
		EntityUtils.copy(habitanteDto, habitante);
		if (habitanteDto.hasEndereco()) {

			habitante.clearEnderecos();
			Endereco end = null;
			for (EnderecoDTO endDto : habitanteDto.getEnderecos()) {
				end = new Endereco();
				EntityUtils.copy(endDto, end);
				habitante.addEndereco(end);
			}
		}

		return habitante;
	}

	private HttpHeaders configHeaderLocation(HabitanteDTO habitanteDto) {
		UriComponents uriComponents = UriComponentsBuilder.fromPath("/habitantes/{codigo}")
				.buildAndExpand(habitanteDto == null ? "" : habitanteDto.getCodigo());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponents.toUri());
		return headers;
	}

}
