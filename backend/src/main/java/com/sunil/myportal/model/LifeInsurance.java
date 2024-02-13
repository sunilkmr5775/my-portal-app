package com.sunil.myportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "life_insurance")
public class LifeInsurance {

	@Id
	@NotNull
	@Column(name = "POLICY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long policyId;

	@Column(name = "POLICY_NO")
	private String policyNo;

	@Column(name = "PLAN_NAME")
	private String planName;

	@Column(name = "POLICY_STATUS")
	private String policyStatus;

	@Column(name = "STATUS")
	private boolean status;

	@Column(name = "SUM_ASSURED")
	private BigDecimal sumAssured;

	@Column(name = "COMMENCEMENT_DATE")
	private LocalDate commencementDate;

	@Column(name = "LAST_PAYMENT_DATE")
	private LocalDate lastPaymentDate;

	@Column(name = "MATURITY_DATE")
	private LocalDate maturityDate;

	@Column(name = "PREMIUM")
	private BigDecimal premium;

	@Column(name = "BANK")
	private String bank;

	@Column(name = "DUE_DATE_MODE")
	private String dueDateMode;  // ANNUALLY, HY, QUATERLY, MONTHLY

	@Column(name = "NOMINEE")
	private String nominee;

	@Column(name = "POLICY_TERM")
	private Long policyTerm;

	@Column(name = "PREMIUM_PAYING_TERM")
	private Long premiumPayingTerm;

	@Column(name = "PREMIUMS_PAID")
	private Long premiumsPaid;

	@Column(name = "PREMIUMS_REMAINING")
	private Long premiumsRemaining;

	@Column(name = "IMG_PATH")
	private String imgPath;

	@Column(name = "HTML_COLOR1")
	private String htmlColor1;

	@Column(name = "HTML_COLOR2")
	private String htmlColor2;

	@Column(name = "HEX_COLOR")
	private String hexColor;

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

	@OneToMany(mappedBy = "lifeInsurance", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Premiums> premiumSet = new LinkedHashSet<>();

}
