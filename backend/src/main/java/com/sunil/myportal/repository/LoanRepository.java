package com.sunil.myportal.repository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunil.myportal.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	Loan findByLoanNo(String loanNo);

	LinkedHashSet<Loan> findByStatus(String statusActive);

	Set<Loan> findAllByOrderByStatusAsc();

	Set<Loan> findByOrderByStatusAsc(); 
	
	@Query(value = "{call FilterLoanRecord(:loanNo, :loanStatus, :bankName)}", nativeQuery = true)
	public ArrayList<Loan> findAllLoanStoredProcedure(@Param("loanNo") String loanNo, @Param("loanStatus") String loanStatus, @Param("bankName") String bankName);

	void deleteByLoanNo(Loan loan);

	List<Loan> findAllByLoanStatus(boolean active);

}
