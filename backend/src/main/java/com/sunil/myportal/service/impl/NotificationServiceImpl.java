package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.ConstantVariables;
import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.dto.NotificationRequest;
import com.sunil.myportal.model.*;
import com.sunil.myportal.repository.ApplicationPropertiesRepository;
import com.sunil.myportal.repository.EventRepository;
import com.sunil.myportal.repository.NotificationRepository;
import com.sunil.myportal.repository.TaskRepository;
import com.sunil.myportal.service.EmailService;
import com.sunil.myportal.service.NotificationService;
import com.sunil.myportal.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    ApplicationPropertiesRepository applicationPropertiesRepository;

    @Autowired
    EmailService emailService;


    @Override
    public Notification addNotification(NotificationRequest notificationRequest) {
        Notification notification = new Notification();
        Notification response = new Notification();
        EventMaster event = new EventMaster();
        try {
            event = eventRepository.findByModule(notificationRequest.getModule());
            //  isLoanDetailsUpdated = updateLoanCounter(loan, emi);
            //	if(isLoanDetailsUpdated) {
            notification.setEvent(event);
            notification.setUserToNotify(notificationRequest.getUserToNotify());
            notification.setStatus(notificationRequest.getStatus());
            notification.setDeleted(false);
            notification.setCreatedBy("sunilkmr5775");
            notification.setCreatedDate(LocalDateTime.now());
            response = this.notificationRepository.save(notification);
        } catch (Exception e) {
            System.out.println("Inside addNotification() in NotificationServiceImpl at line no 60: " + e.getMessage());
        }
        return response;

    }

    @Override
    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notificationRepository.findAll());
    }

    @Override
    public BaseResponse deleteNotification(Long notificationId) {
        BaseResponse baseResponse = new BaseResponse();
        Notification notification = this.notificationRepository.getById(notificationId);
        Long updatedTaskId = 0L;
        try {
            if (notification != null) {
                //task.setTaskStatus(StatusConstant.STATUS_DELETED);
                notification.setDeleted(true);
                notification.setModifiedBy("sunilkmr5775");
                notification.setModifiedDate(LocalDateTime.now());
                this.notificationRepository.save(notification);
                baseResponse.setStatus(StatusConstant.STATUS_SUCCESS);
                baseResponse.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_EC);
                baseResponse.setErrorDescription(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);

                return baseResponse;
            }
        } catch (Exception e) {
            baseResponse.setStatus(StatusConstant.STATUS_FAILURE);
            baseResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
            baseResponse.setErrorDescription(e.getMessage());
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse sendNotification() {
        BaseResponse response = new BaseResponse();
        String content;
        String subject;
        String receiverEmail = "sunilkmr5775@gmail.com";
        List<TaskMaster> pendingTaskList = taskRepository.findAllByTaskStatusAndIsDeleted(StatusConstant.STATUS_PENDING, false);
        for (TaskMaster pendingTask : pendingTaskList) {
            subject = pendingTask.getTitle();
            Long diff = CommonUtil.findDateDifferenceWithCurrentDate(pendingTask.getPlannedEndDate());
            if (diff < 0) {
                content = ConstantVariables.TASK_NOTIFICATION_CONTENT;
                content = content.replace("[[name]]", pendingTask.getCreatedBy());
                content = content.replace("[[task]]", pendingTask.getTitle());
                content = content.replace("[[plannedEndDate]]", pendingTask.getPlannedEndDate().toString());
                response = emailService.sendEmailNotification(content, subject, receiverEmail);
            }
        }
        return response;
    }



/*	@Override
	public void deleteTask(Long taskId) {
		TaskMaster taskMaster = new TaskMaster();
		taskMaster.setId(taskId);
		taskMaster.setDeleted(true);
		this.taskRepository.save(taskMaster);
	}*/

    @Override
    public Notification getNotificationById(Long notificationId) {
        Notification notification = this.notificationRepository.findById(notificationId).get();
        return notification;
    }

	/*@Override
	public List<TaskMaster> sortByTaskTitle(String sortDir) {
		String sortBy = "title";
//	    String sortDir = "DESC";
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		List<TaskMaster> task = this.taskRepository.findAll(sort);
		return task;
	}

	@Override
	public List<TaskMaster> getTaskRecordsByFiter( String taskTitle, String taskStatus) {
		List<TaskMaster> jobList = this.taskRepository.findTaskMasterDetailsByTitleAndTaskStatus(taskTitle, taskStatus);
		return jobList;
	}*/

}
