package com.sunil.myportal.service.impl;

import com.sunil.myportal.model.Country;
import com.sunil.myportal.repository.*;
import com.sunil.myportal.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CountryServiceImpl implements CountryService {


    @Autowired
    CountryRepository countryRepository;

    @Autowired
    StateRepository stateRepository;

    @Override
    public Country addCountry(Country country) {
        country.setCreatedBy("sunilkmr5775");
        country.setCreatedDate(LocalDateTime.now());
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Country country) {
        country.setModifiedBy("sunilkmr5775");
        country.setModifiedDate(LocalDateTime.now());
        return countryRepository.save(country);
    }

    @Override
    public ArrayList<Country> getAllCountries() {
        return (ArrayList<Country>) countryRepository.findAll();
    }

    @Override
    public Country getCountryByState(String stateName) {
        return countryRepository.findByName(stateName);
    }

    @Override
    public void deleteCountry(Long countryId) {
        countryRepository.deleteById(countryId);
    }

    @Override
    public Country getCountryById(Long countryId) {
        return countryRepository.findById(countryId).get();
    }
}
