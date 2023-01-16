package com.sunil.myportal.controller;

import com.sunil.myportal.config.SystemInfo;
import com.sunil.myportal.dto.DatabaseResponse;
import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.model.DatabaseMaster;
import com.sunil.myportal.service.BankMasterService;
import com.sunil.myportal.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@RequestMapping("/database-details")
@CrossOrigin("*")
public class DatabaseController {



    @Autowired
    private DatabaseService databaseService;



    //	 ADD SYSTEM INFO
    @GetMapping
    public DatabaseResponse getDbDetails() throws SQLException {
        SystemInfo si = new SystemInfo();
        return this.databaseService.getDbDetails();
//       return env.getProperty("spring.datasource.url");

    }
}
