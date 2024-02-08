package com.sunil.myportal.repository;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sunil.myportal.model.Emi;
import com.sunil.myportal.model.Loan;

@Repository
public interface EmiRepository extends JpaRepository<Emi, Long> {

	
	Set<Emi> findByLoan(Loan loan);

	@Query("select e from Emi e inner join fetch e.loan where e.eId=:id")
	Emi findAllByEid(Long id);

	boolean existsByEmiDate(LocalDate currentDate);

	Emi findByEmiDateBetween(LocalDate fistDayOfCurrentMonth, LocalDate lastDayOfCurrentMonth);

	Emi findByPaymentStatus(String statusUnpaid);

	Emi findByLoanNoAndPaymentStatusAndEmiDateBetween(String loanId, String statusUnpaid, LocalDate fistDayOfCurrentMonth, LocalDate lastDayOfCurrentMonth);

//    void findByLoanId(Long loanId);
}
