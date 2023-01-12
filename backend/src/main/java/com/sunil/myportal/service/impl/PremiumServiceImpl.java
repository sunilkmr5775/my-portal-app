package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.model.LifeInsurance;
import com.sunil.myportal.model.Premiums;
import com.sunil.myportal.repository.LifeInsuranceRepository;
import com.sunil.myportal.repository.PremiumRepository;
import com.sunil.myportal.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class PremiumServiceImpl implements PremiumService {

	@Autowired
	private PremiumRepository premiumRepository;

	@Autowired
	private LifeInsuranceRepository lifeInsuranceRepository;

	@Override
	public Premiums addPremium(Premiums premium) {
		LifeInsurance lifeInsurance = new LifeInsurance();
		Premiums premium1 = new Premiums();
		boolean isPolcyDetailsUpdated = false;
		try {
			lifeInsurance = lifeInsuranceRepository.findByPolicyNo(premium.getPolicyNo());
//			BigDecimal defaultAamount = new BigDecimal("0.00");
			isPolcyDetailsUpdated = updatePolicyCounter(lifeInsurance, premium.isPremiumStatus());
		if(isPolcyDetailsUpdated) {
			premium.setLifeInsurance(lifeInsurance);
			premium.setPremiumAmount(premium.getPremiumAmount());
			premium.setPremiumDate(premium.getPremiumDate());
			premium.setStatus(premium.isPremiumStatus() == true ? StatusConstant.STATUS_PAID : premium.isPremiumStatus() == false ? StatusConstant.STATUS_UNPAID.toString() : StatusConstant.STATUS_UNKNOWN);
			premium.setPremiumStatus(premium.isPremiumStatus());
			premium.setCreatedBy("sunilkumar5775");
			premium.setCreatedDate(LocalDateTime.now());
			premium.setNoOfPayment(lifeInsurance.getPremiumsPaid());
			premium.setSumAssured(premium.getSumAssured());

			premium1 = this.premiumRepository.save(premium);
			if (lifeInsurance.getPolicyTerm() * 12 == premium.getNoOfPayment()) {
				lifeInsurance.setStatus(false);
				this.lifeInsuranceRepository.save(lifeInsurance);
			}
		  }
		} catch (Exception e) {
			System.out.println("Inside addPremium() in PremiumServiceImpl at line no 55: " + e.getMessage());
		}
		return premium1;
		
	}

	private boolean updatePolicyCounter(LifeInsurance lifeInsurance, boolean premiumStatus) {
		Long policyId = 0L;
		boolean flag = false;
		if(premiumStatus) {

			lifeInsurance.setPremiumsPaid(lifeInsurance.getPremiumsPaid() + 1);
			lifeInsurance.setPremiumsRemaining(lifeInsurance.getPremiumsRemaining()-1);
//			loanDetails.setEmiAmount(emi.getEmiAmount());
//			loanDetails.setInterestPaid(loanDetails.getInterestPaid() == null ? new BigDecimal("0.00") : loanDetails.getInterestPaid().add(emi));
			lifeInsurance.setModifiedBy("sunilkmr5775");
			lifeInsurance.setModifiedDate(LocalDateTime.now());
			policyId = lifeInsuranceRepository.save(lifeInsurance).getPolicyId();

			if (policyId > 0)
				flag= true;
			else
				flag= false;
		} 
		
		return flag;
	}

	@Override
	public Premiums updatePremium(Premiums premium) {
		premium.setModifiedBy(premium.getCreatedBy());
		premium.setModifiedDate(null);
		return this.premiumRepository.save(premium);
	}

	@Override
	public Set<Premiums> getPremiums() {
		return new LinkedHashSet<>(this.premiumRepository.findAll());
	}

	@Override
	public Premiums getPremium(Long premiumId) {
		Premiums premium = this.premiumRepository.findAllById(premiumId);
		return premium;
	}

	@Override
	public void deletePremium(Long premiumId) {
		this.premiumRepository.deleteById(premiumId);

	}

	@Override
	public List<Premiums> getPremiumOfLifeInsurance(LifeInsurance lifeInsurance) {
		// TODO Auto-generated method stub
		return this.premiumRepository.findBylifeInsurance(lifeInsurance);
	}

}
