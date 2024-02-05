package com.sunil.myportal.service;

import com.sunil.myportal.model.Backup;

import java.util.List;

public interface BackupService {
    String createDatabaseDump();

    List<Backup> getAllDatabaseDump();

    void deleteBackup(Long id);
}
