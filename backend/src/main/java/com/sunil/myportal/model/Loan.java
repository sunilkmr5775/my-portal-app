package com.sunil.myportal.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LOAN")
public class Loan {

	@Id
	@NotNull
	@Column(name = "LOAN_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long loanId;

	@Column(name = "LOAN_TYPE")
	private String loanType;

	@Column(name = "LOAN_NO")
	private String loanNo;

	@Column(name = "LOAN_AMOUNT")
	private BigDecimal loanAmount;

	@Column(name = "TOTAL_EMI")
	private Long totalEmi;

	@Column(name = "EMI_PAID")
	private Long emiPaid;

	@Column(name = "EMI_REMAINING")
	private Long emiRemaining;

	@Column(name = "EMI_AMOUNT")
	private BigDecimal emiAmount;

	@Column(name = "INTEREST_TYPE")
	private String interestType;

	@Column(name = "INTEREST_RATE")
	private BigDecimal interestRate;

	@Column(name = "DISBURSAL_DATE")
	private LocalDate disbursalDate;

	@Column(name = "LAST_PAID_EMI_DATE")
	private LocalDate lastPaidEmiDate;

	@Column(name = "FIRST_EMI_DATE")
	private LocalDate firstEmiDate;

	@Column(name = "LAST_EMI_DATE")
	private LocalDate lastEmiDate;

	@Column(name = "LOAN_STATUS")
	private boolean loanStatus;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "LOGO_NAME")
	private String logoName;

	@Column(name = "BANK")
	private String bank;

//@ManyToOne
//@JoinColumn(name="bank", nullable=false)
//private BankMaster bankMaster;

	@Column(name = "EXTRA_ATTR2")
	private String extAttr2;

	@Column(name = "EXTRA_ATTR3")
	private String extAttr3;

	@Column(name = "EXTRA_ATTR4")
	private String extAttr4;

	@Column(name = "CREATED_BY")
	private String createdBy;

	/// @CreationTimestamp
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	/// @UpdateTimestamp
	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modifiedDate;

	@OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
//	@JsonIgnore
	private Set<Emi> emiSet = new LinkedHashSet<>();

	public Loan() {
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public Set<Emi> getEmiSet() {
		return emiSet;
	}

	public void setEmiSet(Set<Emi> emiSet) {
		this.emiSet = emiSet;
	}

	public String getLoanType() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	public boolean isLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(boolean loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

//	public String getBank() {
//		return bank;
//	}
//
//	public void setBank(String bank) {
//		this.bank = bank;
//	}

	public String getExtAttr2() {
		return extAttr2;
	}

	public void setExtAttr2(String extAttr2) {
		this.extAttr2 = extAttr2;
	}

	public String getExtAttr3() {
		return extAttr3;
	}

	public void setExtAttr3(String extAttr3) {
		this.extAttr3 = extAttr3;
	}

	public String getExtAttr4() {
		return extAttr4;
	}

	public void setExtAttr4(String extAttr4) {
		this.extAttr4 = extAttr4;
	}

}
