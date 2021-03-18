package com.api.heroes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.heroes.dto.HeroDTO;

@Entity
@Table(name = "Heroes")
public class Hero implements Serializable {

	private static final long serialVersionUID = 6260916742725562864L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double power;

	public Hero() {
	}

	public Hero(HeroDTO heroDTO) {
		this.id = heroDTO.getId();
		this.name = heroDTO.getName();
		this.power = heroDTO.getPower();
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
