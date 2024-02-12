package com.sunil.myportal.service;

import com.sunil.myportal.model.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CountryService {


	Country addCountry(Country country);

	Country updateCountry(Country country);

	ArrayList<Country> getAllCountries();

	Country getCountryByState(String state);

	void deleteCountry(Long countryId);

	Country getCountryById(Long countryId);
}
