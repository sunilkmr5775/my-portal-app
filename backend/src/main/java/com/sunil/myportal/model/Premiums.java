package com.sunil.myportal.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PREMIUM_HISTORY")
public class Premiums {

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "POLICY_NO")
	private String policyNo;

	@Column(name = "PREMIUM_AMOUNT")
	private BigDecimal premiumAmount;

	@Column(name = "NO_OF_PAYMENT")
	private long noOfPayment;

	@Column(name = "PREMIUM_DATE")
	private LocalDate premiumDate;

	@Column(name = "SUM_ASSURED")
	private BigDecimal sumAssured;

	@Column(name = "PREMIUM_STATUS")
	private boolean premiumStatus;

	@Column(name = "STATUS")
	private String status;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="POLICY_ID")
	private LifeInsurance lifeInsurance;


}
