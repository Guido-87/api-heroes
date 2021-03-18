package com.api.heroes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.api.heroes.model.Hero;

public interface HeroesRepository extends CrudRepository<Hero, Long> {

	List<Hero> findByNameContainingIgnoreCase(String name);
}