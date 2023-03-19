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
public class NotificationRequest {

	private static final long serialVersionUID = 1L;

	private String userToNotify;
	private String cronExpression;
	private String module;
	private String status;
	private boolean isDeleted;

}
