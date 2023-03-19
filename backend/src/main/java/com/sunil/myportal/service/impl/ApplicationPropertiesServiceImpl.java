package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.ConstantVariables;
import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.AppPropertiesRequest;
import com.sunil.myportal.dto.AppPropertiesResponse;
import com.sunil.myportal.model.ApplicationProperties;
import com.sunil.myportal.repository.ApplicationPropertiesRepository;
import com.sunil.myportal.service.ApplicationPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Service
public class ApplicationPropertiesServiceImpl implements ApplicationPropertiesService {

	@Autowired
	private ApplicationPropertiesRepository applicationPropertiesRepository;

	@Override
	public AppPropertiesResponse addAttribute(@Valid AppPropertiesRequest propertiesRequest) {

		AppPropertiesResponse appPropertiesResponse = new AppPropertiesResponse();
		ApplicationProperties appProperties = new ApplicationProperties();

		//appProperties.setId(1L);
		appProperties.setAttributeType(propertiesRequest.getAttributeType());
		appProperties.setKey(propertiesRequest.getRuleKey());
		appProperties.setValue(propertiesRequest.getRuleValue());
		appProperties.setStatus(propertiesRequest.getStatus());
		appProperties.setCreatedBy("sunilkmr5775");
		appProperties.setCreatedDate(LocalDateTime.now());
		try {
			Long id = applicationPropertiesRepository.save(appProperties).getId();

			if (id != null && id > 0) {
				appPropertiesResponse.setStatus(StatusConstant.STATUS_SUBMITTED);
				appPropertiesResponse.setAttributeType(propertiesRequest.getAttributeType());
				appPropertiesResponse.setRuleKey(propertiesRequest.getRuleKey());
				appPropertiesResponse.setRuleValue(propertiesRequest.getRuleValue());
				appPropertiesResponse.setCreatedBy("sunilkmr5775");
				appPropertiesResponse.setCreatedDate(LocalDateTime.now());
				appPropertiesResponse.setErrorCode(ConstantVariables.SUCCESS_RESPONSE_CODE);
				appPropertiesResponse.setErrorDescription(ConstantVariables.SUCCESS_RESPONSE_MSG);
			} else {
				try {
					appPropertiesResponse.setStatus(StatusConstant.STATUS_FAILURE);
					appPropertiesResponse.setErrorCode(ExceptionConstant.REGULAR_ERROR_OCCURED_EC);
					appPropertiesResponse.setErrorDescription(ExceptionConstant.REGULAR_ERROR_OCCURED_ED);
				} catch (Exception e) {
					appPropertiesResponse.setStatus(StatusConstant.STATUS_FAILURE);
					appPropertiesResponse.setErrorCode(ExceptionConstant.REGULAR_ERROR_OCCURED_EC);
					appPropertiesResponse
							.setErrorDescription(ExceptionConstant.REGULAR_ERROR_OCCURED_ED + " : " + e.getMessage());
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			appPropertiesResponse.setStatus(StatusConstant.STATUS_FAILURE);
			appPropertiesResponse.setErrorCode(ExceptionConstant.REGULAR_ERROR_OCCURED_EC);
			appPropertiesResponse.setErrorDescription(ExceptionConstant.REGULAR_ERROR_OCCURED_EC + " : " + ex.getMessage());
		}

		return appPropertiesResponse;
	}

}
