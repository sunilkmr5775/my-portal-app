package com.sunil.myportal.service;

import com.sunil.myportal.dto.DatabaseResponse;
import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.model.DatabaseMaster;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface DatabaseService {

	public DatabaseResponse getDbDetails() throws SQLException;

}
