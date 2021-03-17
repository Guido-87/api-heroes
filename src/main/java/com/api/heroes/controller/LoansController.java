package com.api.heroes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.heroes.dto.LoanDTO;
import com.api.heroes.dto.PagingDTO;
import com.api.heroes.model.Loan;
import com.api.heroes.repository.LoansRepository;
import com.api.heroes.response.LoansResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/loans")
public class LoansController {

	private static final Long ZERO = 0L;
	private final LoansRepository loansRepository;

	public LoansController(final LoansRepository loansRepository) {
		this.loansRepository = loansRepository;
	}

	@GetMapping()
	public ResponseEntity<LoansResponse> getByPageSizeAndUserId(@RequestParam Integer page, @RequestParam Integer size,
			@RequestParam(required = false) Long user_id) throws JsonProcessingException {
		List<LoanDTO> loansDTO = new ArrayList<LoanDTO>();
		Pageable paging = PageRequest.of(page, size);
		Page<Loan> pageLoans;
		Long total = ZERO;
		if (user_id == null) {
			pageLoans = loansRepository.findAll(paging);
		} else {
			pageLoans = loansRepository.findByUserId(user_id, paging);
		}
		if (pageLoans.hasContent()) {
			for (Loan l : pageLoans.getContent()) {
				loansDTO.add(new LoanDTO(l));
				total += l.getTotal();
			}
		}
		PagingDTO pagingDTO = new PagingDTO(page, size, total);
		LoansResponse response = new LoansResponse(loansDTO, pagingDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}