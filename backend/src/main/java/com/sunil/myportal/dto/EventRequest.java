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
public class EventRequest {

	private static final long serialVersionUID = 1L;

	private String module;
	private String eventType;
	private String status;
//	private boolean isDeleted;

}
