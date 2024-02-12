package com.sunil.myportal.controller;

import com.sunil.myportal.model.State;
import com.sunil.myportal.service.CountryService;
import com.sunil.myportal.service.StateService;
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
@RequestMapping("/state")
public class StateController {

	@Autowired
	private CountryService countryService;

	@Autowired
	private StateService stateService;

//	 ADD State
	@PostMapping("/")
	public State addState(@Validated @RequestBody State state)
			throws UnsupportedOperationException, URISyntaxException, IOException {
		return stateService.addState(state);

	}

//	 UPDATE State
	@PutMapping("/")
	public ResponseEntity<State> updateState(@RequestBody State state) {
		State state2 = this.stateService.updateState(state);
		return ResponseEntity.ok(state2);

	}

//	 GET ALL States
	@GetMapping("/")
	public List<State> getAllStates() {
		return new ArrayList<>(this.stateService.getAllStates());
	}

//	 GET State BY City
	@GetMapping("/{city}")
	public Set<State> getStateByCity(@PathVariable String city) {
		return new HashSet<>((Collection) this.stateService.getStateByCity(city));
	}

//	 DELETE State BY ID
	@DeleteMapping("/{stateId}")
	public void deleteState(@PathVariable Long stateId) {
		this.stateService.deleteState(stateId);
	}


//	 GET State BY ID
	@GetMapping("/{stateId}")
	public State getStateById(@PathVariable Long stateId) {
		return this.stateService.getStateById(stateId);
	}


}
