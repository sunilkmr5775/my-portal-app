package com.sunil.myportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COUNTRY")
public class Country {

	@Id
	@NotNull
	@Column(name = "COUNTRY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "NAME")
	private String name;

	@Column(name = "STATUS")
	private String status;

	@JsonIgnore
	@Column(name = "CREATED_BY")
	private String createdBy;

	@JsonIgnore
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@JsonIgnore
	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modifiedDate;


	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
//	@JsonIgnore
	private Set<State> states = new LinkedHashSet<>();

	public Country() {
	}

}
