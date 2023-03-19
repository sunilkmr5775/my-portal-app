package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.dto.EventRequest;
import com.sunil.myportal.dto.LoanRequest;
import com.sunil.myportal.dto.LoanResponse;
import com.sunil.myportal.model.*;
import com.sunil.myportal.repository.EventRepository;
import com.sunil.myportal.repository.LoanRepository;
import com.sunil.myportal.repository.LoanTypeRepository;
import com.sunil.myportal.service.EventService;
import com.sunil.myportal.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public BaseResponse addEvent(EventRequest eventRequest)
			throws UnsupportedOperationException, URISyntaxException, IOException {

		BaseResponse response = new BaseResponse();
		EventMaster event = new EventMaster();
		Long eventId = 0L;
		try {

			event.setEventType(eventRequest.getEventType());
			event.setModule(eventRequest.getModule());
			event.setStatus(eventRequest.getStatus());
			event.setCreatedBy("sunilkmr5775");
			event.setCreatedDate(LocalDateTime.now());
			event.setModifiedBy(null);
			event.setModifiedDate(null);
			eventId = eventRepository.save(event).getEventId();
			try {
				if (eventId > 0) {
					response.setStatus(StatusConstant.STATUS_SUCCESS);
					response.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_EC);
					response.setErrorDescription(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);
					return response;
				}
			} catch (Exception e) {
				response.setStatus(StatusConstant.STATUS_FAILURE);
				response.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
				response.setErrorDescription(e.getMessage());
				return response;
			}
		} catch (Exception ex) {
			response.setStatus(StatusConstant.STATUS_FAILURE);
			response.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
			response.setErrorDescription(ex.getMessage());
			return response;

		}
		return response;
	}

	@Override
	public List<EventMaster> getAllEvents() {
		return new ArrayList<>(eventRepository.findAll());
	}

	@Override
	public EventMaster getEventById(Long eventId) {
		return this.eventRepository.findByEventId(eventId);

	}

}
