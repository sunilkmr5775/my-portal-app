package com.sunil.myportal.repository;

import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.model.PolicyPremiumMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PolicyPremiumModeRepository extends JpaRepository<PolicyPremiumMode, Long> {

//	@Query(value = "SELECT * FROM BANK_MASTER ORDER BY BANK_NAME", nativeQuery = true)
	public Set<PolicyPremiumMode> findAllByOrderByPremiumModeAsc();
	
	public Set<PolicyPremiumMode> findAllByOrderByPremiumModeDesc();

}
