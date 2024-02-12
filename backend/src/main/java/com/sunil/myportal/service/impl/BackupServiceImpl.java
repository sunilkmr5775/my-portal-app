package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.model.Backup;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.repository.BackupRepository;
import com.sunil.myportal.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BackupServiceImpl implements BackupService {

    @Autowired
    private BackupRepository backupRepository;

    // Set the cron expression for scheduling. Below is an example for daily backup at 2 AM.
    @Scheduled(cron = "0 23 16 ? * *")
    public String createDatabaseDump() {
        // Perform the database dump and save relevant information.
        // Replace this with your actual database dump logic.

        // For example, you can use mysqldump command or any other preferred approach.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String backupFileName = "backup_" + timestamp + ".sql";
        String backupPath = "C:\\Users\\HP\\OneDrive\\Documents\\MyDocuments\\MySql_Dumps\\";
        String dumpFile = backupPath+backupFileName;
        String message = "";
        String result = performBackup(dumpFile);
        // Save backup information to the database.
        if (result.equals("200")) {
            Backup backup = new Backup();
            backup.setFilePath(backupPath);
            backup.setFileName(backupFileName);
            backup.setCreatedDate(LocalDateTime.now());
            backupRepository.save(backup);
            System.out.println("Database dump created: " + backupFileName);
            message = "Database dump "+dumpFile+" created successfully.";
        } else {
            message = result;
        }
        return message;

    }

    @Override
    public List<Backup> getAllDatabaseDump() {
        return new ArrayList<>(this.backupRepository.findAll());
    }

    @Override
    public void deleteBackup(Long id) {
        String message = null;
       try{
           this.backupRepository.deleteById(id);
            message = "Backup deleted successfully.";
       } catch (Exception ex){
           ex.printStackTrace();
       }
      // return message;
    }

    public String performBackup(String backupFileName) {
        String result = null;
        String database = "my-portal-db";
        String user = "root";
        String password = "password";


        int exitCode ;

        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump -u " + user + " -p" + password + " " + database + " -r " + backupFileName;
//      String executeCmd = "\"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump\" -u " + user + " -p" + password + " " + database + " -r " + backupFileName;

        try {
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            exitCode = runtimeProcess.waitFor();

            if (exitCode == 0) {
                result = "200";
            } else {
                result = "Backup failed. Exit code: " + exitCode;
                System.err.println("Backup failed. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
