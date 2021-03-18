package com.api.heroes.dto;

import java.io.Serializable;

import com.api.heroes.model.Hero;

public class HeroDTO implements Serializable {

	private static final long serialVersionUID = 2039427636535359746L;
	private Long id;
	private String name;
	private Double power;

	public HeroDTO() {
	}

	public HeroDTO(Hero hero) {
		this.id = hero.getId();
		this.name = hero.getName();
		this.power = hero.getPower();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}
}
