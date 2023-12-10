package com.sunil.myportal.service;

import com.sunil.myportal.dto.TaskRequest;
import com.sunil.myportal.dto.TaskResponse;
import com.sunil.myportal.model.TaskMaster;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    public List<TaskMaster> getAllTasks();

    public TaskResponse deleteTask(Long taskId);

    public TaskMaster getTaskById(Long taskId);

    public List<TaskMaster> sortByTaskTitle(String direction);


    TaskResponse addNewTask(TaskRequest taskRequest);

    TaskResponse updateTask(TaskRequest taskRequest);

    TaskResponse markTaskAsCompleted(Long taskId);

    TaskResponse markTaskAsUndone(Long taskId);

    List<TaskMaster> getAllActiveTasks();

    List<TaskMaster> getTaskRecordsByFilter(String taskTitle, String taskStatus, String taskYear, String taskMonth);

    TaskResponse rollOverTask(List<Long> rollOverTaskList);
}
