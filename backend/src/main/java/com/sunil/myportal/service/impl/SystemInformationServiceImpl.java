package com.sunil.myportal.service.impl;

import com.sunil.myportal.dto.SystemInformationResponse;
import com.sunil.myportal.service.SystemInformationService;
import com.sunil.myportal.util.RuntimeUtil;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service
public class SystemInformationServiceImpl implements SystemInformationService {

	@Override
	public SystemInformationResponse getSystemInformationDetails() throws SQLException {
		SystemInformationResponse systemInformationResponse = null;
		try {
			systemInformationResponse = new SystemInformationResponse();

			systemInformationResponse.setMaximumHeap(RuntimeUtil.getMaxMemoryInMiB());
			systemInformationResponse.setCurrent(RuntimeUtil.getTotalMemoryInMiB());
			systemInformationResponse.setUsed(RuntimeUtil.getUsedMemoryInMiB());
			systemInformationResponse.setFree(RuntimeUtil.getFreeMemoryInMiB());
			systemInformationResponse.setPercentageUsed(RuntimeUtil.getPercentageUsedFormatted());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return systemInformationResponse;
	}

}