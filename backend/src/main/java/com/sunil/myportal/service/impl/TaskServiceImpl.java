package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.TaskRequest;
import com.sunil.myportal.dto.TaskResponse;
import com.sunil.myportal.model.TaskMaster;
import com.sunil.myportal.repository.TaskRepository;
import com.sunil.myportal.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskResponse addNewTask(TaskRequest taskRequest) {
		TaskResponse taskResponse = new TaskResponse();
		TaskMaster task = new TaskMaster();
		Long taskId = 0L;
		try {

			task.setTitle(taskRequest.getTitle());
			task.setDescription(taskRequest.getDescription());
			task.setPlannedStartDate(taskRequest.getPlannedStartDate());
			task.setPlannedEndDate(taskRequest.getPlannedEndDate());
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
	public List<TaskMaster> getAllTasks() {
//		return new ArrayList<>(this.bankMasterRepository.findAllByOrderByBankNameAsc());
		return new ArrayList<>(taskRepository.findAll(Sort.by(Sort.Direction.ASC, "plannedStartDate")));
	}

	@Override
	public void deleteTask(Long taskId) {
		TaskMaster taskMaster = new TaskMaster();
		taskMaster.setId(taskId);
		this.taskRepository.delete(taskMaster);
	}

	@Override
	public TaskMaster getTaskById(Long jobId) {
		TaskMaster task = this.taskRepository.findById(jobId).get();
		return task;
	}

	@Override
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
	}

}
