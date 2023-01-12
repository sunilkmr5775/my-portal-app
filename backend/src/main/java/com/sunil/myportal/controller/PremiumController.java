package com.sunil.myportal.controller;

import com.sunil.myportal.model.LifeInsurance;
import com.sunil.myportal.model.Premiums;
import com.sunil.myportal.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Component
@RequestMapping("/premiums")
@CrossOrigin("*")
public class PremiumController {
	

	@Autowired
	private PremiumService premiumService;

//	 ADD PREMIUM
	@PostMapping("/")
	public ResponseEntity<Premiums> addPremium(@RequestBody Premiums premium) {
		System.out.println("PREMIUM ADDED");
		return ResponseEntity.ok(this.premiumService.addPremium(premium));

	}

//	 UPDATE PREMIUMS
	@PutMapping("/")
	public ResponseEntity<Premiums> updateEmi(@RequestBody Premiums premium) {
		if (premium.getCreatedDate() == null) {
			premium.setCreatedDate(LocalDateTime.now());
		}
		System.out.println("PREMIUM Updated");
		return ResponseEntity.ok(this.premiumService.updatePremium(premium));

	}

//	 GET ALL PREMIUMS
	@GetMapping("/")
	public ResponseEntity<?> getPemiums() {
		return ResponseEntity.ok(this.premiumService.getPremiums());
	}

//	 GET SINGLE PREMIUMS BY ID
	@GetMapping("/{premiumId}")
	public Premiums getPremiumById(@PathVariable("premiumId") Long premiumId) {
		return this.premiumService.getPremium(premiumId);
	}

//	 DELETE PREMIUM BY ID
	@DeleteMapping("/{premiumId}")
	public void deletePremium(@PathVariable("premiumId") Long premiumId) {
		this.premiumService.deletePremium(premiumId);

	}
	
	@GetMapping("/life-insurance/{policyId}")
	public List<Premiums> getPremiumOfLifeInsurance(@PathVariable("policyId") Long policyId){
		LifeInsurance lifeInsurance = new LifeInsurance();
		lifeInsurance.setPolicyId(policyId);
		return this.premiumService.getPremiumOfLifeInsurance(lifeInsurance);
	}
	


}
