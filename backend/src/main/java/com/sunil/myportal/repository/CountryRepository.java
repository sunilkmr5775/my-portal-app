package com.sunil.myportal.repository;

import com.sunil.myportal.model.Country;
import com.sunil.myportal.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByName(String stateName);

    Country findByCountryCode(String countryCode);
}
