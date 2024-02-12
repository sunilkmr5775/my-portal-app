package com.sunil.myportal.service;

import com.sunil.myportal.model.State;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface StateService {


    State addState(State state);

    State updateState(State state);

    ArrayList<State> getAllStates();

    Object getStateByCity(String city);

    void deleteState(Long stateId);

    State getStateById(Long stateId);
}
