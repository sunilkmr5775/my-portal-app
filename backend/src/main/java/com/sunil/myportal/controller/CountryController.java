package com.sunil.myportal.controller;

import com.sunil.myportal.model.Country;
import com.sunil.myportal.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@Component
@CrossOrigin("*")
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

//	 ADD LOAN
	@GetMapping("/testMsg")
	public String testApi() {
		return "This is a Testing API";

	}

	@PostMapping("/")
	public Country addCountry(@Validated @RequestBody Country country)
			throws UnsupportedOperationException, URISyntaxException, IOException {
		return countryService.addCountry(country);

	}

//	 UPDATE COUNTRY
	@PutMapping("/")
	public ResponseEntity<Country> updateCountry(@RequestBody Country country) {
		country.setModifiedDate(LocalDateTime.now());
		Country country2 = this.countryService.updateCountry(country);
		return ResponseEntity.ok(country2);

	}

//	 GET ALL COUNTRIES
	@GetMapping("/")
	public List<Country> getAllCountries() {
		return new ArrayList<>(this.countryService.getAllCountries());
	}

//	 GET COUNTRY BY STATE
	@GetMapping("/{state}")
	public Set<Country> getCountryByState(@PathVariable String state) {
		return new HashSet<>((Collection) this.countryService.getCountryByState(state));
	}

//	 DELETE COUNTRY BY ID
	@DeleteMapping("/{countryId}")
	public void deleteCountry(@PathVariable Long countryId) {
		this.countryService.deleteCountry(countryId);
	}


//	 GET COUNTRY BY ID
	@GetMapping("/countryId/{countryId}")
	public Country getCountryById(@PathVariable Long countryId) {
		return this.countryService.getCountryById(countryId);
	}


}
