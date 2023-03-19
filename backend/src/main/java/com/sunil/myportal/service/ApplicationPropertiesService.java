package com.sunil.myportal.service;

import com.sunil.myportal.dto.AppPropertiesRequest;
import com.sunil.myportal.dto.AppPropertiesResponse;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public interface ApplicationPropertiesService {

	AppPropertiesResponse addAttribute(@Valid AppPropertiesRequest propertiesRequest);
}
