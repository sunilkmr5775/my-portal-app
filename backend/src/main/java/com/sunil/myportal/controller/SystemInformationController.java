package com.sunil.myportal.controller;

import com.sunil.myportal.dto.DatabaseResponse;
import com.sunil.myportal.dto.SystemInformationResponse;
import com.sunil.myportal.service.SystemInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Component
@RequestMapping("/system-information")
@CrossOrigin("*")
public class SystemInformationController {



    @Autowired
    private SystemInformationService systemInformationService;



//  ADD SYSTEM INFO
    @GetMapping
    public SystemInformationResponse getSystemInformationDetails() throws SQLException {
        return this.systemInformationService.getSystemInformationDetails();

    }
}
