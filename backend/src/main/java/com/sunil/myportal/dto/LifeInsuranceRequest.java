package com.sunil.myportal.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
public class LifeInsuranceRequest {

	private static final long serialVersionUID = 1L;

	private String policyNo;
	private String bank;
	private String planName;
	private BigDecimal sumAssured;
	private LocalDate commencementDate;
	private LocalDate lastPaymentDate;
	private LocalDate maturityDate;
	private BigDecimal premium;
	private String dueDateMode;
  	private Long policyTerm;
  	private Long premiumPayingTerm;
	private Long premiumsPaid;
	private Long premiumsRemaining;
	private String nominee;
	private String policyStatus;
	private boolean status;

	private String imgPath;
	private String htmlColor1;
	private String htmlColor2;
	private String hexColor;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;

}