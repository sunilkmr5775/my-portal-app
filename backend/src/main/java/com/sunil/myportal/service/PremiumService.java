package com.sunil.myportal.service;

import com.sunil.myportal.model.Emi;
import com.sunil.myportal.model.LifeInsurance;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.model.Premiums;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PremiumService {
	
	
	public Premiums addPremium(Premiums premium);
	
	public Premiums updatePremium(Premiums premium);
	
	public Set<Premiums> getPremiums();
	
	public Premiums getPremium(Long premiumId);
	
	public void deletePremium(Long premiumId);
	
	public List<Premiums> getPremiumOfLifeInsurance(LifeInsurance lifeInsurance);
	
}
