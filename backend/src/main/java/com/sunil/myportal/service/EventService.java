package com.sunil.myportal.service;

import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.dto.EventRequest;
import com.sunil.myportal.dto.LoanRequest;
import com.sunil.myportal.dto.LoanResponse;
import com.sunil.myportal.model.EventMaster;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.model.LoanTypes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

@Service
public interface EventService {

	public BaseResponse addEvent(EventRequest eventRequest) throws UnsupportedOperationException, URISyntaxException, IOException ;

    public List<EventMaster> getAllEvents();

    EventMaster getEventById(Long eventId);
}
