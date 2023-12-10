package com.sunil.myportal.controller;

import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.dto.TaskDefinition;
import com.sunil.myportal.service.TaskDefinitionBean;
import com.sunil.myportal.service.TaskSchedulingService;
import com.sunil.myportal.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.*;

@RestController
@Component
@RequestMapping("/tasks")
@CrossOrigin("*")
@EnableScheduling
public class SchedulerController {

    @Autowired
    private TaskSchedulingService taskSchedulingService;

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;


    @PostMapping(path = "/taskdef", consumes = "application/json", produces = "application/json")
    public void scheduleATask(@RequestBody TaskDefinition taskDefinition) {
        taskDefinitionBean.setTaskDefinition(taskDefinition);
        taskSchedulingService.scheduleATask(taskDefinitionBean, taskDefinition.getCronExpression());
    }

    @GetMapping(path = "/remove/{jobid}")
    public void removeJob(@PathVariable String jobid) {
        taskSchedulingService.removeScheduledTask(jobid);
    }

//    @Scheduled(cron = "0/12 0 * * * *")  // trigger every 12 hours
    @Scheduled(cron = "0 0/2 * * * ?")  // trigger job every 2 mins
    public BaseResponse sendTaskNotification() {
        return this.taskSchedulingService.sendTaskNotification();
    }

//    @Scheduled(cron = "0 0 * * * *")  // trigger job every day at 12:00 AM
    @Scheduled(cron = "0 0/2 * * * ?")  // trigger job every 2 mins
//    @GetMapping(path = "/schedule")
    public BaseResponse sendEmiPaidNotification() {
        return this.taskSchedulingService.updateEmiAndSendEmiPaidNotification();
    }

    @Scheduled(cron = "0 0 * * * *")  // trigger job every day at 12:00 AM
//  @GetMapping(path = "/schedule")
    public void resetTaskNotification() {
        this.taskSchedulingService.resetTaskNotification();
    }

}
