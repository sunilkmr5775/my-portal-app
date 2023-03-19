package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.TaskRequest;
import com.sunil.myportal.dto.TaskResponse;
import com.sunil.myportal.model.TaskMaster;
import com.sunil.myportal.repository.TaskRepository;
import com.sunil.myportal.service.TaskService;
import com.sunil.myportal.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            task.setPlannedStartDate(taskRequest.getPlannedStartDate() == null ?
                    LocalDate.now() : taskRequest.getPlannedStartDate());
            task.setPlannedEndDate(taskRequest.getPlannedEndDate() == null ?
                    LocalDate.now() : taskRequest.getPlannedEndDate());
            task.setActualStartDate(taskRequest.getActualStartDate());
            task.setActualEndDate(taskRequest.getActualEndDate());
            task.setTaskStatus(StatusConstant.STATUS_PENDING);
            task.setPriority(taskRequest.getPriority());
            task.setCreatedBy("sunilkmr5775");
            task.setCreatedDate(LocalDate.now());
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
            if (task != null) {
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
            if (task != null) {
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
    }

    @Override
    public List<TaskMaster> getAllActiveTasks() {
        return new ArrayList<>(taskRepository.findAllByTaskStatus(StatusConstant.STATUS_PENDING));
    }


    @Override
    public List<TaskMaster> getAllTasks() {
        LocalDate taskFirstDate = CommonUtil.getFirstDateOfTheMonth(LocalDate.now());
        LocalDate taskLastDate = CommonUtil.getLastDateOfTheMonth(LocalDate.now());
        List<TaskMaster> pendingTaskList = new ArrayList<>(taskRepository.
                findAllByIsDeletedAndByCreatedDate(false, taskFirstDate, taskLastDate));

        /* List<TaskMaster> filteredTaskList =
                pendingTaskList.stream().filter(a ->(
                a.getCreatedDate().compareTo(CommonUtil.getCurrentDate()) >= 0 ||
                a.getPlannedEndDate().compareTo(CommonUtil.getCurrentDate()) >= 0 ||
                a.getActualEndDate().compareTo(CommonUtil.getCurrentDate()) >= 0
        )).collect(Collectors.toList());*/
        return pendingTaskList;
    }

    @Override
    public TaskResponse deleteTask(Long taskId) {
        TaskResponse taskResponse = new TaskResponse();
        TaskMaster task = this.taskRepository.getById(taskId);
        Long updatedTaskId = 0L;
        try {
            if (task != null) {
                //task.setTaskStatus(StatusConstant.STATUS_DELETED);
                task.setDeleted(true);
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

/*	@Override
	public void deleteTask(Long taskId) {
		TaskMaster taskMaster = new TaskMaster();
		taskMaster.setId(taskId);
		taskMaster.setDeleted(true);
		this.taskRepository.save(taskMaster);
	}*/

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
    public List<TaskMaster> getTaskRecordsByFilter(String taskTitle, String taskStatus, String taskYear, String taskMonth) {
        String inputTaskDate=taskYear+"-"+taskMonth+"-01";
        LocalDate taskFirstDate = CommonUtil.getFirstDateOfTheMonth(CommonUtil.convertStringToLocalDate(inputTaskDate));
        LocalDate taskLastDate = CommonUtil.getLastDateOfTheMonth(CommonUtil.convertStringToLocalDate(inputTaskDate));

        List<TaskMaster> jobList = this.taskRepository.findTaskMasterDetailsByTitleAndTaskStatusAndByCreatedDate(taskTitle, taskStatus,
                taskFirstDate, taskLastDate);
        return jobList;
    }

}
