package com.sunil.myportal.controller;

import com.sunil.myportal.config.SystemInfo;
import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.dto.NotificationRequest;
import com.sunil.myportal.dto.TaskResponse;
import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.model.Emi;
import com.sunil.myportal.model.Notification;
import com.sunil.myportal.service.BankMasterService;
import com.sunil.myportal.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@RequestMapping("/notifications")
@CrossOrigin("*")
public class NotificationController {


    @Autowired
    private NotificationService notificationService;

    //	 ADD EMI
    @PostMapping("/")
    public ResponseEntity<Notification> addNotification(@RequestBody NotificationRequest notificationRequest) {
        System.out.println("NOTIFICATION Added");
        return ResponseEntity.ok(this.notificationService.addNotification(notificationRequest));

    }

    //	 GET ALL Notifications
    @GetMapping("/")
    public List<Notification> getAllNotifications() {
        return new ArrayList<>(this.notificationService.getAllNotifications());

    }

    //	 GET SINGLE Notification BY ID
    @GetMapping("/{notificationId}")
    public Notification getNotificationById(@PathVariable("notificationId") Long notificationId) {
        return this.notificationService.getNotificationById(notificationId);

    }

    @GetMapping("/send-notification")
    public BaseResponse sendNotification() {
        return this.notificationService.sendNotification();

    }

    //	 DELETE Notification BY ID
    @DeleteMapping("/{notificationId}")
    public BaseResponse deleteNotification(@PathVariable Long notificationId) {
       return this.notificationService.deleteNotification(notificationId);

    }

}
