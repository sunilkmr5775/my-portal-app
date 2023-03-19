package com.sunil.myportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "NOTIFICATION")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="EVENT_ID")
	private EventMaster event;

	@Column(name = "USER_TO_NOTIFY")
	private String userToNotify;

	@Column(name = "CRON_EXPRESSION")
	private String cronExpression;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "IS_DELETED")
	private boolean isDeleted;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modifiedDate;



}
