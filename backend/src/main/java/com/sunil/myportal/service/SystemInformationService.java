package com.sunil.myportal.service;

import com.sunil.myportal.dto.DatabaseResponse;
import com.sunil.myportal.dto.SystemInformationResponse;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface SystemInformationService {

	public SystemInformationResponse getSystemInformationDetails() throws SQLException;

}
