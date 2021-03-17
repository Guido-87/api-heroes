package com.api.heroes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.heroes.model.Loan;

public interface LoansRepository extends JpaRepository<Loan, Long> {

	public Page<Loan> findByUserId(Long userId, Pageable pageable);
}