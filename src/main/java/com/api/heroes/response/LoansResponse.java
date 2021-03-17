package com.api.heroes.response;

import java.io.Serializable;
import java.util.List;

import com.api.heroes.dto.LoanDTO;
import com.api.heroes.dto.PagingDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "items", "paging" })
public class LoansResponse implements Serializable {

	private static final long serialVersionUID = 2774021160633657880L;
	private List<LoanDTO> items;
	private PagingDTO paging;

	public LoansResponse() {

	}

	public LoansResponse(List<LoanDTO> items, PagingDTO paging) {
		this.items = items;
		this.paging = paging;
	}

	public List<LoanDTO> getItems() {
		return items;
	}

	public void setItems(List<LoanDTO> items) {
		this.items = items;
	}

	public PagingDTO getPaging() {
		return paging;
	}

	public void setPaging(PagingDTO paging) {
		this.paging = paging;
	}

}
