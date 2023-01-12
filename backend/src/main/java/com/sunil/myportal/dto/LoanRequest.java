package com.sunil.myportal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import com.sunil.myportal.model.Emi;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
public class LoanRequest  {

	private static final long serialVersionUID = 1L;

	private String loanType;
	private String loanNo;
	private BigDecimal loanAmount;
	private String bank;
	private Long totalEmi;
	private Long emiPaid;
	private Long emiRemaining;
	private BigDecimal emiAmount;
	private String interestType;
	private BigDecimal interestRate;
	private LocalDate disbursalDate;
	private LocalDate firstEmiDate;
	private LocalDate lastEmiDate;
	private boolean loanStatus;
	private String status;
	private BigDecimal interestPaid;
	private String logoName;
	
/*	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Long getTotalEmi() {
		return totalEmi;
	}
	public void setTotalEmi(Long totalEmi) {
		this.totalEmi = totalEmi;
	}
	public Long getEmiPaid() {
		return emiPaid;
	}
	public void setEmiPaid(Long emiPaid) {
		this.emiPaid = emiPaid;
	}
	public Long getEmiRemaining() {
		return emiRemaining;
	}
	public void setEmiRemaining(Long emiRemaining) {
		this.emiRemaining = emiRemaining;
	}
	public BigDecimal getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(BigDecimal emiAmount) {
		this.emiAmount = emiAmount;
	}
	public String getInterestType() {
		return interestType;
	}
	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public LocalDate getDisbursalDate() {
		return disbursalDate;
	}
	public void setDisbursalDate(LocalDate disbursalDate) {
		this.disbursalDate = disbursalDate;
	}
	public LocalDate getFirstEmiDate() {
		return firstEmiDate;
	}
	public void setFirstEmiDate(LocalDate firstEmiDate) {
		this.firstEmiDate = firstEmiDate;
	}
	public LocalDate getLastEmiDate() {
		return lastEmiDate;
	}
	public void setLastEmiDate(LocalDate lastEmiDate) {
		this.lastEmiDate = lastEmiDate;
	}
	
	
	public boolean isLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(boolean loanStatus) {
		this.loanStatus = loanStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getInterestPaid() {
		return interestPaid;
	}
	public void setInterestPaid(BigDecimal interestPaid) {
		this.interestPaid = interestPaid;
	}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}*/

	
}
