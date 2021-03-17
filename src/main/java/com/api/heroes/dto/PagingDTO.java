package com.api.heroes.dto;

import java.io.Serializable;

public class PagingDTO implements Serializable {

	private static final long serialVersionUID = 8751870948141310431L;
	private Integer page;
	private Integer size;
	private Long total;

	public PagingDTO(Integer page, Integer size, Long total) {
		this.page = page;
		this.size = size;
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
