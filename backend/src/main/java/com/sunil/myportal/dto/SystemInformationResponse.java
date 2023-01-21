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
public class SystemInformationResponse {

	private static final long serialVersionUID = 1L;

	String current;
	String used;
	String free;
	String maximumHeap;
	String percentageUsed;
}