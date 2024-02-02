package com.sunil.myportal;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySQLBackup {

    public static void main(String[] args) {
        String database = "my-portal-db";
        String user = "root";
        String password = "password";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String backupFileName = "backup_" + timestamp + ".sql";
        String backupPath = "E:\\MySql\\"+backupFileName;
        int status ;

        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump -u " + user + " -p" + password + " " + database + " -r " + backupPath;

        try {
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            status = runtimeProcess.waitFor();
            if (status == 0) {
                System.out.println("Backup taken successfully");
            } else {
                System.out.println("Could not take mysql backup");
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }
}
