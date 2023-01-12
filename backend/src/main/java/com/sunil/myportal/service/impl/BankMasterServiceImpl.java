package com.sunil.myportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.repository.BankMasterRepository;
import com.sunil.myportal.service.BankMasterService;

@Service
public class BankMasterServiceImpl implements BankMasterService {

	@Autowired
	private BankMasterRepository bankMasterRepository;

	@Override
	public List<BankMaster> getAllBanks() {
//		return new ArrayList<>(this.bankMasterRepository.findAllByOrderByBankNameAsc());
		return new ArrayList<>(bankMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "bankName")));
	}

	@Override
	public void deleteBank(Long bankId) {
		BankMaster bankMaster = new BankMaster();
		bankMaster.setId(bankId);
		this.bankMasterRepository.delete(bankMaster);

	}

	@Override
	public BankMaster getBank(Long bankId) {
		BankMaster bank = this.bankMasterRepository.findById(bankId).get();
		return bank;
	}

	@Override
	public List<BankMaster> sortByBankName(String sortDir) {
		String sortBy = "bankName";       
//	     String sortDir = "DESC";

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		List<BankMaster> bank = this.bankMasterRepository.findAll(sort);
		return bank;
	}

}
