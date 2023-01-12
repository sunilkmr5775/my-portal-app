package com.sunil.myportal.controller;

import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.model.PolicyPremiumMode;
import com.sunil.myportal.service.BankMasterService;
import com.sunil.myportal.service.PolicyPremiumModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@RequestMapping("/premium-modes")
@CrossOrigin("*")
public class PolicyPremiumModeController {

	@Autowired
	private PolicyPremiumModeService policyPremiumModeService;

//	 GET ALL PAYMENT-MODES
	@GetMapping("/")
	public List<PolicyPremiumMode> getAllPremiumModes() {
		return new ArrayList<>(this.policyPremiumModeService.getAllPremiumModes());

	}

//	 GET SINGLE Bank BY ID
	@GetMapping("/{premiumModeId}")
	public PolicyPremiumMode getPremiumModeById(@PathVariable("premiumModeId") Long premiumModeId) {
		return this.policyPremiumModeService.getPremiumModeById(premiumModeId);

	}

//	 DELETE CATEGORY BY ID
	@DeleteMapping("/{premiumModeId}")
	public void deletePremiumMode(@PathVariable Long premiumModeId) {
		this.policyPremiumModeService.deletePremiumMode(premiumModeId);

	}

	@GetMapping("/sort")
	public List<PolicyPremiumMode> sortByBankName(@RequestParam String direction) {
		return this.policyPremiumModeService.sortByPremiumMode(direction);
	}

}
