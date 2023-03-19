package com.sunil.myportal.controller;

import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.dto.EventRequest;
import com.sunil.myportal.dto.LoanRequest;
import com.sunil.myportal.dto.LoanResponse;
import com.sunil.myportal.model.EventMaster;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.model.LoanTypes;
import com.sunil.myportal.model.Notification;
import com.sunil.myportal.service.EventService;
import com.sunil.myportal.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Component
@CrossOrigin("*")
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;


	@PostMapping("/")
	public BaseResponse addEvent(@RequestBody EventRequest eventRequest)
			throws UnsupportedOperationException, URISyntaxException, IOException {
		return eventService.addEvent(eventRequest);
	}

	//	 GET ALL Events
	@GetMapping("/")
	public List<EventMaster> getAllEvents() {
		return new ArrayList<>(this.eventService.getAllEvents());
	}

	//	 GET SINGLE Event BY ID
	@GetMapping("/{eventId}")
	public EventMaster getNotificationById(@PathVariable("eventId") Long eventId) {
		return this.eventService.getEventById(eventId);
	}

}
