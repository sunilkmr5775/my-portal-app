package com.sunil.myportal.service;

import java.util.List;
import java.util.Set;

import com.sunil.myportal.dto.BankRequest;
import com.sunil.myportal.dto.BaseResponse;
import org.springframework.stereotype.Service;

import com.sunil.myportal.model.BankMaster;

@Service
public interface BankMasterService {

	public List<BankMaster> getAllBanks();

	public void deleteBank(Long bankId);

	public BankMaster getBank(Long bankId);

	public List<BankMaster> sortByBankName(String direction);

	BaseResponse addBank(BankRequest bankRequest);
}
