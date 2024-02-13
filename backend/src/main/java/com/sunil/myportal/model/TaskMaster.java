package com.sunil.myportal.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "task_master")
public class TaskMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "TASK_STATUS")
	private String taskStatus;

	@Column(name = "PRIORITY")
	private String priority;

	@Column(name = "IS_DELETED")
	private boolean isDeleted;

	@Column(name = "PLANNED_START_DATE")
	private LocalDate plannedStartDate;

	@Column(name = "PLANNED_END_DATE")
	private LocalDate plannedEndDate;

	@Column(name = "ACTUAL_START_DATE")
	private LocalDate actualStartDate;

	@Column(name = "ACTUAL_END_DATE")
	private LocalDate actualEndDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private LocalDate createdDate;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modifiedDate;

}
