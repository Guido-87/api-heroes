package com.api.heroes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.api.heroes.dto.LoanDTO;
import com.api.heroes.dto.UserDTO;

@Entity
@Table(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = 6260916742725562864L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Loan> loans;

	public User() {
	}

	public User(UserDTO userDTO) {
		this.id = userDTO.getId();
		this.email = userDTO.getEmail();
		this.firstName = userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.loans = new ArrayList<Loan>();
		if (userDTO.getLoans() != null && !userDTO.getLoans().isEmpty()) {
			for (LoanDTO l : userDTO.getLoans()) {
				this.loans.add(new Loan(l));
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
}
