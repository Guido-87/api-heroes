package com.api.heroes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.heroes.dto.HeroDTO;
import com.api.heroes.model.Hero;
import com.api.heroes.repository.HeroesRepository;
import com.api.heroes.response.HeroesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/heroes")
public class HeroesController {

	private final HeroesRepository heroesRepository;

	public HeroesController(final HeroesRepository heroesRepository) {
		this.heroesRepository = heroesRepository;
	}

	@GetMapping()
	public ResponseEntity<HeroesResponse> getAllOrGetByName(@RequestParam(required = false) String name)
			throws JsonProcessingException {
		List<HeroDTO> heroesDTO = new ArrayList<HeroDTO>();
		List<Hero> heroesResult = new ArrayList<Hero>();
		if (StringUtils.hasText(name)) {
			heroesResult = heroesRepository.findByNameContainingIgnoreCase(name);
		} else {
			Iterable<Hero> allHeroes = heroesRepository.findAll();
			allHeroes.forEach(heroesResult::add);
		}
		if (!heroesResult.isEmpty() && heroesResult.size() > 0) {
			for (Hero hero : heroesResult) {
				heroesDTO.add(new HeroDTO(hero));
			}
		}
		HeroesResponse response = new HeroesResponse(heroesDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> findById(@PathVariable("id") Long id) throws JsonProcessingException {
		Hero hero = heroesRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found."));
		HeroDTO heroDTO = new HeroDTO(hero);
		String jsonString = new ObjectMapper().writeValueAsString(heroDTO);
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Hero updatedHero,
			BindingResult result) {
		Hero heroToUpdate = heroesRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id " + id + " not found."));
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Binding error.");
		}
		if (StringUtils.hasText(updatedHero.getName())) {
			heroToUpdate.setName(updatedHero.getName());
		}
		if (null != updatedHero.getPower()) {
			heroToUpdate.setPower(updatedHero.getPower());
		}
		heroToUpdate = heroesRepository.save(heroToUpdate);
		if (heroToUpdate != null) {
			return new ResponseEntity<>("Hero updated succesfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error updating hero.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			heroesRepository.deleteById(id);
			return new ResponseEntity<>("Hero deleted succesfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting hero.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<String> create(@Validated @RequestBody HeroDTO heroDTO, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Binding error.");
		}
		if (heroDTO.getId() != null && heroesRepository.existsById(heroDTO.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id already exists.");
		}
		Hero newHero = new Hero(heroDTO);
		try {
			Hero createdHero = heroesRepository.save(newHero);
			if (createdHero != null) {
				return new ResponseEntity<>("Hero created succesfully.", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Error creating hero.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error creating hero.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}