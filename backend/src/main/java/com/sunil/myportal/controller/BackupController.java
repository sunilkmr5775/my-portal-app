package com.sunil.myportal.controller;

import com.sunil.myportal.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/backups")
public class BackupController {

    @Autowired
    private BackupService backupService;

    @GetMapping("/create")
    public String createDatabaseDump() {
        return backupService.createDatabaseDump();

    }
}
