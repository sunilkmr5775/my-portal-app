package com.sunil.myportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
public class TaskResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private String title;
	private String description;
	private String plannedStartDate;
	private String plannedEndDate;
	private String actualStartDate;
	private String actualEndDate;
	private String taskStatus;
	private String priority;
}
