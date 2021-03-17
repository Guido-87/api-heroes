package com.api.heroes.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.api.heroes.model.Loan;
import com.api.heroes.model.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 2039427636535359746L;
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private List<LoanDTO> loans;

	public UserDTO() {

	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.loans = new ArrayList<LoanDTO>();
		if (user.getLoans() != null && !user.getLoans().isEmpty()) {
			for (Loan l : user.getLoans()) {
				this.loans.add(new LoanDTO(l));
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

	public List<LoanDTO> getLoans() {
		return loans;
	}

	public void setLoans(List<LoanDTO> loans) {
		this.loans = loans;
	}
}
