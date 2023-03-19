package com.sunil.myportal.service;

import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.dto.NotificationRequest;
import com.sunil.myportal.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

	Notification addNotification(NotificationRequest notificationRequest);

	public List<Notification> getAllNotifications();

	public Notification getNotificationById(Long notificationId);

	public BaseResponse deleteNotification(Long notificationId);

	BaseResponse sendNotification();

}
