package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.TaskDefinition;
import com.sunil.myportal.model.TaskMaster;
import com.sunil.myportal.repository.TaskRepository;
import com.sunil.myportal.service.TaskDefinitionBean;
import com.sunil.myportal.service.TaskSchedulingService;
import com.sunil.myportal.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Service
public class TaskSchedulingServiceImpl implements TaskSchedulingService {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private TaskRepository taskRepository;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();


	public void scheduleATask(TaskDefinitionBean tasklet, String cronExpression) {
        String jobId = CommonUtil.generateUuid();
        System.out.println("Scheduling task with job id: " + jobId + " and cron expression: " + cronExpression);

        List<TaskMaster> pendingTaskList = this.taskRepository.findAllByTaskStatus(StatusConstant.STATUS_PENDING);
        for(TaskMaster pendingTask: pendingTaskList){
            System.out.println("pendingTask: "+pendingTask.getDescription());
           // tasklet.setTaskDefinition(pendingTask.getDescription());
            ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
            jobsMap.put(jobId, scheduledTask);
        }

    }

	public void removeScheduledTask(String jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }
}
