package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.dto.TaskRequest;
import com.sunil.myportal.dto.TaskResponse;
import com.sunil.myportal.model.Notification;
import com.sunil.myportal.model.TaskMaster;
import com.sunil.myportal.repository.NotificationRepository;
import com.sunil.myportal.repository.TaskRepository;
import com.sunil.myportal.service.NotificationService;
import com.sunil.myportal.service.TaskService;
import com.sunil.myportal.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private TaskRepository taskRepository;

	/*@Override
	public TaskResponse addNewTask(TaskRequest taskRequest) {
		TaskResponse taskResponse = new TaskResponse();
		TaskMaster task = new TaskMaster();
		Long taskId = 0L;
		try {

			task.setTitle(taskRequest.getTitle());
			task.setDescription(taskRequest.getDescription());
			task.setPlannedStartDate(taskRequest.getPlannedStartDate()==null?
					LocalDate.now():taskRequest.getPlannedStartDate());
			task.setPlannedEndDate(taskRequest.getPlannedEndDate()==null?
					LocalDate.now():taskRequest.getPlannedEndDate());
			task.setActualStartDate(taskRequest.getActualStartDate());
			task.setActualEndDate(taskRequest.getActualEndDate());
			task.setTaskStatus(StatusConstant.STATUS_PENDING);
			task.setPriority(taskRequest.getPriority());
			task.setCreatedBy("sunilkmr5775");
			task.setCreatedDate(LocalDateTime.now());
			task.setModifiedBy(null);
			task.setModifiedDate(null);
			taskId = taskRepository.save(task).getId();
			try {
				if (taskId > 0) {
					taskResponse.setTitle(taskRequest.getTitle());
					taskResponse.setStatus(StatusConstant.STATUS_SUCCESS);
					taskResponse.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_EC);
					taskResponse.setErrorDescription(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);

					return taskResponse;
				}
			} catch (Exception e) {
				taskResponse.setStatus(StatusConstant.STATUS_FAILURE);
				taskResponse.setTitle(taskRequest.getTitle());
				taskResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
				taskResponse.setErrorDescription(e.getMessage());
				return taskResponse;
			}
		} catch (Exception ex) {
			taskResponse.setStatus(StatusConstant.STATUS_FAILURE);
			taskResponse.setTitle(taskRequest.getTitle());
			taskResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
			taskResponse.setErrorDescription(ex.getMessage());
			return taskResponse;
		}
		return taskResponse;
	}

	@Override
	public TaskResponse markTaskAsCompleted(Long taskId) {
		TaskResponse taskResponse = new TaskResponse();
		TaskMaster task = this.taskRepository.getById(taskId);
		Long updatedTaskId = 0L;
		try {
			if (task!=null) {
				task.setTaskStatus(StatusConstant.STATUS_COMPLETED);
				task.setActualEndDate(LocalDate.now());
				task.setModifiedBy("sunilkmr5775");
				task.setModifiedDate(LocalDateTime.now());
				this.taskRepository.save(task);
				taskResponse.setTitle(task.getTitle());
				taskResponse.setStatus(StatusConstant.STATUS_SUCCESS);
				taskResponse.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_EC);
				taskResponse.setErrorDescription(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);

				return taskResponse;
			}
		} catch (Exception e) {
			taskResponse.setStatus(StatusConstant.STATUS_FAILURE);
			taskResponse.setTitle(task.getTitle());
			taskResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
			taskResponse.setErrorDescription(e.getMessage());
			return taskResponse;
		}
		return taskResponse;
	}

	@Override
	public TaskResponse markTaskAsUndone(Long taskId) {
		TaskResponse taskResponse = new TaskResponse();
		TaskMaster task = this.taskRepository.getById(taskId);
		Long updatedTaskId = 0L;
		try {
			if (task!=null) {
				task.setTaskStatus(StatusConstant.STATUS_PENDING);
				task.setModifiedBy("sunilkmr5775");
				task.setModifiedDate(LocalDateTime.now());
				this.taskRepository.save(task);
				taskResponse.setTitle(task.getTitle());
				taskResponse.setStatus(StatusConstant.STATUS_SUCCESS);
				taskResponse.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_EC);
				taskResponse.setErrorDescription(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);

				return taskResponse;
			}
		} catch (Exception e) {
			taskResponse.setStatus(StatusConstant.STATUS_FAILURE);
			taskResponse.setTitle(task.getTitle());
			taskResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
			taskResponse.setErrorDescription(e.getMessage());
			return taskResponse;
		}
		return taskResponse;
	}*/


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
			if (notification!=null) {
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
	public Notification sendNotification() {
		LocalDate currentDate = CommonUtil.getCurrentDate();
		List<TaskMaster> pendingTaskList = taskRepository.findAllByTaskStatus(StatusConstant.STATUS_PENDING);
		for(TaskMaster pendingTask:pendingTaskList){
			Long diff = CommonUtil.findDateDifferenceWithCurrentDate(pendingTask.getPlannedEndDate());
			if(diff>0){
				boolean sendNotification = sendNotificationToUser();
			}
		};

	}

	private boolean sendNotificationToUser() {


		boolean foo = false;
		String TEMP_SENDER_EMAIL = null;
		String TEMP_PASSWORD = null;
		String SUBJECT = null;


		List<ApplicationProperties> applicationPropertiesList = applicationPropertiesRepository
				.findApplicationPropertiesListByAttributeTypeAndStatus(ConstantVariables.SMTP_ATTRIBUTES,
						StatusConstant.STATUS_ACTIVE);

//      Setup host and mail server
		Properties properties = new Properties();
		try {
			for (ApplicationProperties ruleAttribute : applicationPropertiesList) {
				if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.properties.mail.smtp.auth")) {
					properties.put("mail.smtp.auth", Boolean.parseBoolean(ruleAttribute.getRuleValue()));
				}

				if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.properties.mail.smtp.starttls.enable")) {
					properties.put("mail.smtp.starttls.enable", Boolean.parseBoolean(ruleAttribute.getRuleValue()));
				}

				if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.host")) {
					properties.put("mail.smtp.host", ruleAttribute.getRuleValue());
				}

				if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.port")) {
					properties.put("mail.smtp.port", Integer.parseInt(ruleAttribute.getRuleValue()));
				}
				if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.username")) {
					TEMP_SENDER_EMAIL = ruleAttribute.getRuleValue();
				}
				if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.password")) {
					TEMP_PASSWORD = ruleAttribute.getRuleValue();
				}
				if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.subject")) {
					SUBJECT = ruleAttribute.getRuleValue();
				}
			}

			final String SENDER_EMAIL = TEMP_SENDER_EMAIL;
			final String PASSWORD = TEMP_PASSWORD;

			// get the session object and pass username and password
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(SENDER_EMAIL, PASSWORD);
				}
			});

			String content = ConstantVariables.EMAIL_CONTENT;
			content = content.replace("[[name]]",
					user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());

			String verifyURL = siteURL + "/api/auth/verify?code=" + user.getVerificationCode();
			System.out.println("Verification Code: "+user.getVerificationCode());

			content = content.replace("[[URL]]", verifyURL.trim().toString());

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(content, "text/html");
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(TEMP_SENDER_EMAIL));
			message.setContent(multipart);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			message.setSubject(user.getUsername() + " - " + SUBJECT);
			message.setText(content, "UTF-8", "html");
			Transport.send(message);

			System.out.println("Email Sent With Inline Image Successfully to " + user.getEmail());

			foo = true;

		} catch (Exception e) {

			System.out.println("EmailService File Error: " + e.getMessage());
		}

		return foo;

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
