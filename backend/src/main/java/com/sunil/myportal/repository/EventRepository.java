package com.sunil.myportal.repository;

import com.sunil.myportal.model.EventMaster;
import com.sunil.myportal.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface EventRepository extends JpaRepository<EventMaster, Long> {


    EventMaster findByModule(String module);

    EventMaster findByEventId(Long eventId);
}
