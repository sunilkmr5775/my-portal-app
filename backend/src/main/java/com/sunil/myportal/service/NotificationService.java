package com.sunil.myportal.service;

import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

	public List<Notification> getAllNotifications();

	public Notification getNotificationById(Long notificationId);

	public BaseResponse deleteNotification(Long notificationId);

	Notification sendNotification();
}
