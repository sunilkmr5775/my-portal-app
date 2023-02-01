package com.sunil.myportal.service;

import org.springframework.stereotype.Service;

@Service
public interface TaskSchedulingService {


    public void scheduleATask(TaskDefinitionBean taskDefinitionBean, String cronExpression);

    void removeScheduledTask(String jobid);
}