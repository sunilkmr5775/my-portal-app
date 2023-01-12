package com.sunil.myportal.controller;

import com.sunil.myportal.dto.JobRequest;
import com.sunil.myportal.dto.JobResponse;
import com.sunil.myportal.model.JobMaster;
import com.sunil.myportal.service.JobMasterService;
import com.sunil.myportal.dto.JobRequest;
import com.sunil.myportal.dto.JobResponse;
import com.sunil.myportal.model.JobMaster;
import com.sunil.myportal.service.JobMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@RequestMapping("/jobs")
@CrossOrigin("*")
public class JobMasterController {

	@Autowired
	private JobMasterService jobMasterService;

	@PostMapping("/")
	public JobResponse addNewJob(@Validated @RequestBody JobRequest jobRequest)
			throws UnsupportedOperationException, URISyntaxException, IOException {

		return jobMasterService.addNewJob(jobRequest);

	}
//	 GET ALL Jobs
	@GetMapping("/")
	public List<JobMaster> getAllJobs() {
		return new ArrayList<>(this.jobMasterService.getAllJobs());

	}

//	 GET Job BY ID
	@GetMapping("/id/{jobId}")
	public JobMaster getJobById(@PathVariable("jobId") Long jobId) {
		return this.jobMasterService.getJobById(jobId);

	}

	//	 GET Job BY JOB NAME
	@GetMapping("job-name/{jobName}")
	public JobMaster getJobByName(@PathVariable("jobName") String jobName) {
		return this.jobMasterService.getJobByName(jobName);

	}

	//	 GET Job BY JOB NAME Or STATUS
	//	 GET LOAN BY ID
	@GetMapping("/filterJob")
	public List<JobMaster> getJobRecordsByFiter(
			@RequestParam(value = "jobStatus", required = false) String jobStatus,
			@RequestParam(value = "jobName", required = false) String jobName) {

		if(jobStatus.equalsIgnoreCase("All")	|| jobStatus==""||jobStatus.equals("")){
			jobStatus=null;
		}
		if(jobName.equalsIgnoreCase("All")||jobName==""||jobName.equals("")) {
			jobName=null;
		}
		return new ArrayList<>(this.jobMasterService.findByJobNameOrStatus(jobStatus, jobName));

	}

//	 DELETE JOB BY ID
	@DeleteMapping("/{jobId}")
	public void deleteJob(@PathVariable Long jobId) {
		this.jobMasterService.deleteJob(jobId);

	}

	@GetMapping("/sort")
	public List<JobMaster> sortByJobName(@RequestParam String direction) {
		return this.jobMasterService.sortByJobName(direction);
	}

}
