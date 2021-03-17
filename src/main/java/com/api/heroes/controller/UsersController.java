package com.api.heroes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.heroes.dto.UserDTO;
import com.api.heroes.model.User;
import com.api.heroes.repository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	private final UsersRepository usersRepository;

	public UsersController(final UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> findById(@PathVariable("id") Long id) throws JsonProcessingException {
		User user = usersRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found."));
		UserDTO userDTO = new UserDTO(user);
		String jsonString = new ObjectMapper().writeValueAsString(userDTO);
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<String> create(@Validated @RequestBody UserDTO userDTO, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Binding error.");
		}
		if (userDTO.getId() != null && usersRepository.existsById(userDTO.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id already exists.");
		}
		User newUser = new User(userDTO);
		try {
			User createdUser = usersRepository.save(newUser);
			if (createdUser != null) {
				return new ResponseEntity<>("User created succesfully.", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Error creating user.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error creating user.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			usersRepository.deleteById(id);
			return new ResponseEntity<>("User deleted succesfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting user.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}