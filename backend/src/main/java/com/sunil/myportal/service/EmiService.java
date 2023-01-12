package com.sunil.myportal.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.sunil.myportal.model.Emi;
import com.sunil.myportal.model.Loan;

@Service
public interface EmiService {
	
	
	public Emi addEmi(Emi emi);
	
	public Emi updateEmi(Emi emi);
	
	public Set<Emi> getEmis();
	
	public Emi getEmi(Long emiId);
	
	public void deleteEmi(Long emiId);
	
	public List<Emi> getEmiOfLoan(Loan loan);
	
}
