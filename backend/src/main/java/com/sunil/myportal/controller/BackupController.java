package com.sunil.myportal.controller;

import com.sunil.myportal.model.Backup;
import com.sunil.myportal.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/backups")
@CrossOrigin(origins = "http://localhost:4200")
public class BackupController {

    @Autowired
    private BackupService backupService;

    @GetMapping("/create")
    public String createDatabaseDump() {
        return backupService.createDatabaseDump();

    }

    @GetMapping("/getAllDatabaseDump")
    public List<Backup> getAllDatabaseDump() {
        return new ArrayList<>(this.backupService.getAllDatabaseDump());

    }
    @DeleteMapping("/{id}")
    public void deleteDump(@PathVariable Long id) {
         this.backupService.deleteBackup(id);
    }

}
