package com.api.heroes.response;

import java.io.Serializable;
import java.util.List;

import com.api.heroes.dto.HeroDTO;

public class HeroesResponse implements Serializable {

	private static final long serialVersionUID = 2774021160633657880L;
	private List<HeroDTO> items;

	public HeroesResponse() {
	}

	public HeroesResponse(List<HeroDTO> items) {
		this.items = items;
	}

	public List<HeroDTO> getItems() {
		return items;
	}

	public void setItems(List<HeroDTO> items) {
		this.items = items;
	}
}