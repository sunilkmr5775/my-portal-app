package com.sunil.myportal.controller;

import com.sunil.myportal.dto.AppPropertiesRequest;
import com.sunil.myportal.dto.AppPropertiesResponse;
import com.sunil.myportal.service.ApplicationPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Component
@RequestMapping("/")
public class ApplicationPropertiesController {

	@Autowired
	private ApplicationPropertiesService appPropertiesService;

	@PostMapping("/create-property")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody AppPropertiesRequest propertiesRequest) {

		AppPropertiesResponse propertiesResponse = this.appPropertiesService.addAttribute(propertiesRequest);
		return ResponseEntity.ok(propertiesResponse);
	}
}
