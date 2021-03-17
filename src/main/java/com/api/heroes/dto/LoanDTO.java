package com.api.heroes.dto;

import java.io.Serializable;

import com.api.heroes.model.Loan;
import com.api.heroes.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoanDTO implements Serializable {

	private static final long serialVersionUID = 8563523243121473403L;
	private Long id;
	private Long total;
	@JsonIgnore
	private User user;
	private Long userId;

	public LoanDTO(Loan loan) {
		this.id = loan.getId();
		this.total = loan.getTotal();
		this.user = loan.getUser();
		this.userId = loan.getUser().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
