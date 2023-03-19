package com.sunil.myportal.repository;

import com.sunil.myportal.model.ApplicationProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationPropertiesRepository extends JpaRepository<ApplicationProperties, Long> {



	List<ApplicationProperties> findApplicationPropertiesListByAttributeTypeAndStatus(String smtpAttributes,
																					  String statusActive);

//	AppPropertiesResponse addAttribute(/* @Valid */ AppPropertiesRequest propertiesRequest);
  
	
}
