package com.api.heroes.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.api.heroes.dto.LoanDTO;

@Entity
@Table(name = "Loans")
public class Loan implements Serializable {

	private static final long serialVersionUID = 4505870460257378506L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long total;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;

	public Loan() {
	}

	public Loan(LoanDTO loanDTO) {
		this.id = loanDTO.getId();
		this.total = loanDTO.getTotal();
		this.user = loanDTO.getUser();
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

}
