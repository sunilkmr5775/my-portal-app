package com.sunil.myportal.service;

import com.sunil.myportal.dto.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface TaskSchedulingService {


    public void scheduleATask(TaskDefinitionBean taskDefinitionBean, String cronExpression);

    void removeScheduledTask(String jobid);

    public BaseResponse sendTaskNotification();

    public BaseResponse updateEmiAndSendEmiPaidNotification();

    public void resetTaskNotification();
}