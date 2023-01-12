package com.sunil.myportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sunil.myportal.model.Emi;
import com.sunil.myportal.model.Loan;

@Repository
public interface EmiRepository extends JpaRepository<Emi, Long> {

	
	List<Emi> findByLoan(Loan loan);

	@Query("select e from Emi e inner join fetch e.loan where e.eId=:id")
	Emi findAllByEid(Long id);

}
