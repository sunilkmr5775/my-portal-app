package com.sunil.myportal.controller;

import com.sunil.myportal.dto.TaskDefinition;
import com.sunil.myportal.service.TaskDefinitionBean;
import com.sunil.myportal.service.TaskSchedulingService;
import com.sunil.myportal.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.*;

@RestController
@Component
@RequestMapping("/tasks")
@CrossOrigin("*")
@EnableScheduling
public class ScheduledTasksController {

	/*@Scheduled(cron = "0/5 * * * * ?")
	public void scheduleTaskUsingCronExpression() {
		long now = System.currentTimeMillis() / 1000;
		System.out.println("schedule tasks using cron jobs - " + CommonUtil.getTimeStamp());
	}*/

	@Autowired
	private TaskSchedulingService taskSchedulingService;

	@Autowired
	private TaskDefinitionBean taskDefinitionBean;


	@PostMapping(path="/taskdef", consumes = "application/json", produces="application/json")
	public void scheduleATask(@RequestBody TaskDefinition taskDefinition) {
		taskDefinitionBean.setTaskDefinition(taskDefinition);
		taskSchedulingService.scheduleATask(taskDefinitionBean, taskDefinition.getCronExpression());
	}

	@GetMapping(path="/remove/{jobid}")
	public void removeJob(@PathVariable String jobid) {
		taskSchedulingService.removeScheduledTask(jobid);
	}
}
