package com.sunil.myportal.service.impl;

import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.model.PolicyPremiumMode;
import com.sunil.myportal.repository.BankMasterRepository;
import com.sunil.myportal.repository.PolicyPremiumModeRepository;
import com.sunil.myportal.service.BankMasterService;
import com.sunil.myportal.service.PolicyPremiumModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyPremiumModeServiceImpl implements PolicyPremiumModeService {

	@Autowired
	private PolicyPremiumModeRepository policyPremiumModeRepository;

	@Override
	public List<PolicyPremiumMode> getAllPremiumModes() {
//		return new ArrayList<>(this.bankMasterRepository.findAllByOrderByBankNameAsc());
		return new ArrayList<>(policyPremiumModeRepository.findAll(Sort.by(Sort.Direction.ASC, "premiumMode")));
	}

	@Override
	public void deletePremiumMode(Long premiumModeId) {
		PolicyPremiumMode policyPremiumMode = new PolicyPremiumMode();
		policyPremiumMode.setId(premiumModeId);
		this.policyPremiumModeRepository.delete(policyPremiumMode);

	}

	@Override
	public PolicyPremiumMode getPremiumModeById(Long bankId) {
		PolicyPremiumMode policyPremiumMode = this.policyPremiumModeRepository.findById(bankId).get();
		return policyPremiumMode;
	}

	@Override
	public List<PolicyPremiumMode> sortByPremiumMode(String sortDir) {
		String sortBy = "premiumMode";
//	     String sortDir = "DESC";

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		List<PolicyPremiumMode> policyPremiumMode = this.policyPremiumModeRepository.findAll(sort);
		return policyPremiumMode;
	}

}
