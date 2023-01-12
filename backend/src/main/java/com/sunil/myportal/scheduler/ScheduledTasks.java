package com.sunil.myportal.scheduler;

import org.springframework.stereotype.Component;

import com.sunil.myportal.util.CommonUtil;

@Component
public class ScheduledTasks {

//	@Scheduled(cron = "0/30 * * * * ?")
	public void scheduleTaskUsingCronExpression() {
		long now = System.currentTimeMillis() / 1000;
		System.out.println("schedule tasks using cron jobs - " + CommonUtil.getTimeStamp());
	}


}
