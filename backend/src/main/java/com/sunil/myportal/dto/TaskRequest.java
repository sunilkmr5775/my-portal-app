package com.sunil.myportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
public class TaskRequest {

	private static final long serialVersionUID = 1L;

	private long id;
	private String title;
	private String description;
	private LocalDate plannedStartDate;
	private LocalDate plannedEndDate;
	private LocalDate actualStartDate;
	private LocalDate actualEndDate;
	private String taskStatus;
	private String priority;
	private boolean reminderRequired;

}
