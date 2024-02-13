package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.model.Country;
import com.sunil.myportal.model.Emi;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.model.State;
import com.sunil.myportal.repository.CountryRepository;
import com.sunil.myportal.repository.StateRepository;
import com.sunil.myportal.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class StateServiceImpl implements StateService {


    @Autowired
    CountryRepository countryRepository;

    @Autowired
    StateRepository stateRepository;

    @Override
    public State addState(State state) {
        Country country = new Country();
        State state1 = new State();
        try {
            country = countryRepository.findByCountryCode(state.getCountryCode());
            state.setCountry(country);
            state.setStateCode(state.getStateCode());
            state.setCountryCode(state.getCountryCode());
            state.setName(state.getName());
            state.setCreatedBy("sunilkumar5775");
            state.setCreatedDate(LocalDateTime.now());
            state1 = this.stateRepository.save(state);
        } catch (Exception e) {
            System.out.println("Inside addEmi() in EmiServiceImpl at line no 50: " + e.getMessage());
        }
        return state1;

    }

    @Override
    public State updateState(State state) {
        return stateRepository.save(state);
    }

    @Override
    public ArrayList<State> getAllStates() {
        return (ArrayList<State>) stateRepository.findAll();
    }

    @Override
    public State getStateByCity(String cityName) {
        return stateRepository.findByName(cityName);
    }

    @Override
    public void deleteState(Long stateId) {
        stateRepository.deleteById(stateId);
    }

    @Override
    public State getStateById(Long stateId) {
        return stateRepository.findById(stateId).get();
    }
}
