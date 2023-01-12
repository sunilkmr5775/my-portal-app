package com.sunil.myportal.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sunil.myportal.model.BankMaster;

@Repository
public interface BankMasterRepository extends JpaRepository<BankMaster, Long> {

//	@Query(value = "SELECT * FROM BANK_MASTER ORDER BY BANK_NAME", nativeQuery = true)
	public Set<BankMaster> findAllByOrderByBankNameAsc();
	
	public Set<BankMaster> findAllByOrderByBankNameDesc();

}
