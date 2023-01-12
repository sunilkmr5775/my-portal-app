package com.sunil.myportal.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.sunil.myportal.model.Emi;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.service.EmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RequestMapping("/emi")
@CrossOrigin("*")
public class EmiController {
	

	@Autowired
	private EmiService emiService;

//	 ADD EMI
	@PostMapping("/")
	public ResponseEntity<Emi> addEmi(@RequestBody Emi emi) {
		System.out.println("EMI Added");
		return ResponseEntity.ok(this.emiService.addEmi(emi));

	}

//	 UPDATE EMI
	@PutMapping("/")
	public ResponseEntity<Emi> updateEmi(@RequestBody Emi emi) {
		if (emi.getCreatedDate() == null) {
			emi.setCreatedDate(LocalDateTime.now());
			
		}
		System.out.println("EMI Updated");
		return ResponseEntity.ok(this.emiService.updateEmi(emi));

	}

//	 GET ALL QUIZZES
	@GetMapping("/")
	public ResponseEntity<?> getEmis() {
		return ResponseEntity.ok(this.emiService.getEmis());

	}

//	 GET SINGLE EMI BY ID
	@GetMapping("/{emiId}")
	public Emi getEmi(@PathVariable("emiId") Long emiId) {
		return this.emiService.getEmi(emiId);

	}

//	 DELETE EMI BY ID
	@DeleteMapping("/{emiId}")
	public void deleteEmi(@PathVariable("emiId") Long emiId) {
		this.emiService.deleteEmi(emiId);

	}
	
	@GetMapping("/category/{loanId}")
	public List<Emi> getEmiOfLoan(@PathVariable("loanId") Long loanId){
		Loan loan = new Loan();
		loan.setLoanId(loanId);
		return this.emiService.getEmiOfLoan(loan);
	}
	


}
