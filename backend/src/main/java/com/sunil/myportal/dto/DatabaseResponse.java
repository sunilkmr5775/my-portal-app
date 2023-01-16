package com.sunil.myportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
public class DatabaseResponse {

	private static final long serialVersionUID = 1L;

	private String databasePlatform;
	private String dbVersion;
	private String hostName;
	private String portNo;
	private String dbName;
	private String userName;
	private String password;
	private String status;
	private String profile;

}

