package com.sunil.myportal.dto;

import com.sun.istack.NotNull;
import com.sunil.myportal.model.Emi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanResponse extends BaseResponse {

	private Long loanId;
	private String loanType;
	private String loanNo;
	private BigDecimal loanAmount;
	private Long totalEmi;
	private Long emiPaid;
	private Long emiRemaining;
	private BigDecimal emiAmount;
	private String interestType;
	private BigDecimal interestRate;
	private LocalDate disbursalDate;
	private LocalDate lastPaidEmiDate;
	private LocalDate firstEmiDate;
	private LocalDate lastEmiDate;
	private boolean loanStatus;
	private String status;
	private String logoName;
	private String bank;

	private String extAttr2;
	private String extAttr3;
	private String extAttr4;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	private Set<Emi> emiSet = new LinkedHashSet<>();

}
