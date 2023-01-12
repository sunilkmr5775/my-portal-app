package com.sunil.myportal.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.myportal.dto.LoanRequest;
import com.sunil.myportal.dto.LoanResponse;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.model.LoanTypes;
import com.sunil.myportal.service.LoanService;

@RestController
@Component
@CrossOrigin("*")
@RequestMapping("/loans")
public class LoanController {

	@Autowired
	private LoanService loanService;

//	 ADD LOAN
	@GetMapping("/testMsg")
	public String testApi() {
		return "This is a Testing API";

	}

//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	@ResponseBody
	@PostMapping("/")
	public LoanResponse addNewLoan(@Validated @RequestBody LoanRequest loanRequest)
			throws UnsupportedOperationException, URISyntaxException, IOException {

		return loanService.addNewLoan(loanRequest);

	}

//	 UPDATE LOAN
	@PutMapping("/")
	public ResponseEntity<Loan> updateLoan(@RequestBody Loan loan) {
		loan.setModifiedDate(LocalDateTime.now());
		Loan loan2 = this.loanService.updateLoan(loan);
		return ResponseEntity.ok(loan2);

	}

//	 GET ALL LOANS
	@GetMapping("/")
	public List<Loan> getAllLoans() {
		return new ArrayList<>(this.loanService.getAllLoans());
	}

//	 GET ALL LOANS
	@GetMapping("/active")
	public List<Loan> getAllActiveLoans() {
		return new ArrayList<>(this.loanService.getAllActiveLoans());
	}

//	 GET LOANS BY STATUS
	@GetMapping("/status/{loanStatus}")
	public Set<Loan> getLoansByStatus(@PathVariable String loanStatus) {
		return new HashSet<>(this.loanService.getLoansByStatus(loanStatus));
	}

//	 GET LOAN BY ID
	@GetMapping("/filterLoan")
	public List<Loan> getLoanRecordsByFiter(
//			@PathVariable String loanNo, @PathVariable String loanStatus, @PathVariable String bankName
			@RequestParam(value = "loanNo", required = false) String loanNo,
			@RequestParam(value = "loanStatus", required = false) String loanStatus,
			@RequestParam(value = "bankName", required = false) String bankName) {

		if(loanNo.isEmpty()||loanNo==""||loanNo.equals("")) {
			loanNo=null;
		}
		if(loanStatus.isEmpty()||loanStatus==""||loanStatus.equals("")) {
			loanStatus=null;
		}
		if(bankName.isEmpty()||bankName==""||bankName.equals("")) {
			bankName=null;
		}
		return new ArrayList<>(this.loanService.findAllLoanStoredProcedure(loanNo, loanStatus, bankName));

	}

//	 DELETE LOAN BY ID
	@DeleteMapping("/{loanId}")
	public void deleteCategory(@PathVariable Long loanId) {
		this.loanService.deleteLoan(loanId);
	}
	
//	 DELETE LOAN BY LOAN NO
	/*
	 * @DeleteMapping("/{loanNo}") public void deleteLoan(@PathVariable String
	 * loanNo) { this.loanService.deleteByLoanNo(loanNo); }
	 */

//	 GET LOAN BY ID
	@GetMapping("/{loanId}")
	public Loan getLoan(@PathVariable Long loanId) {
		return this.loanService.getLoan(loanId);

	}
	
//	 GET ALL LOAN TYPES
	@GetMapping("/loanTypes")
	public List<LoanTypes> getAllLoanTypes() {
		return this.loanService.getAllLoanTypes();

	}

}
