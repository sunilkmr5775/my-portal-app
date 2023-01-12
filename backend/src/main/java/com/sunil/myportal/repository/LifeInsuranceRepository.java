package com.sunil.myportal.repository;

import com.sunil.myportal.model.LifeInsurance;
import com.sunil.myportal.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface LifeInsuranceRepository extends JpaRepository<LifeInsurance, Long> {

	LifeInsurance findByPolicyNo(String policyNo);
//
//	LinkedHashSet<Loan> findByStatus(String statusActive);
//
//	Set<Loan> findAllByOrderByStatusAsc();
//
//	Set<Loan> findByOrderByStatusAsc();
//
	@Query(value = "{call FilterLifeInsuranceRecord(:policyNo, :policyStatus, :bankName)}", nativeQuery = true)
	public ArrayList<LifeInsurance> findAllLifeInsuranceStoredProcedure(@Param("policyNo") String policyNo, @Param("policyStatus") String policyStatus, @Param("bankName") String bankName);
//
//	void deleteByLoanNo(Loan loan);
//

    List<LifeInsurance> findAllByStatus(boolean statusActive);
}
