package com.sunil.myportal.controller;

import com.sunil.myportal.dto.JobRequest;
import com.sunil.myportal.dto.JobResponse;
import com.sunil.myportal.dto.TaskRequest;
import com.sunil.myportal.dto.TaskResponse;
import com.sunil.myportal.model.JobMaster;
import com.sunil.myportal.model.TaskMaster;
import com.sunil.myportal.service.JobMasterService;
import com.sunil.myportal.service.TaskService;
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
@RequestMapping("/tasks")
@CrossOrigin("*")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/")
	public TaskResponse addNewTask(@Validated @RequestBody TaskRequest taskRequest)
			throws UnsupportedOperationException, URISyntaxException, IOException {

		return taskService.addNewTask(taskRequest);

	}
//	 GET ALL Tasks
	@GetMapping("/")
	public List<TaskMaster> getAllTasks() {
		return new ArrayList<>(this.taskService.getAllTasks());

	}

	//	 GET ALL ACTIVE Tasks
	@GetMapping("/pending-task")
	public List<TaskMaster> getAllActiveTasks() {
		return new ArrayList<>(this.taskService.getAllActiveTasks());

	}

//	 GET Task BY ID
	@GetMapping("/id/{taskId}")
	public TaskMaster getTaskById(@PathVariable("taskId") Long taskId) {
		return this.taskService.getTaskById(taskId);

	}


	//	 GET Job BY JOB NAME Or STATUS
	//	 GET LOAN BY ID
	@GetMapping("/filterTask")
	public List<TaskMaster> getJobRecordsByFiter(
			@RequestParam(value = "taskTitle", required = false) String taskTitle
			,@RequestParam(value = "taskStatus", required = false) String taskStatus
			){
//		if(taskTitle.equalsIgnoreCase("All")	|| taskTitle==""||taskTitle.equals("")){
		if(taskTitle==null || taskTitle=="" || taskTitle.isEmpty()){
			taskTitle=" ";  // using space bcz it is passed as like in where
		}
		if(taskStatus.equalsIgnoreCase("All")	|| taskStatus==""||taskTitle.equals("")){
			taskStatus=null;
		}

		return new ArrayList<>(this.taskService.getTaskRecordsByFiter(taskTitle, taskStatus));

	}

//	 DELETE TASK BY ID
	@PutMapping("/delete/{taskId}")
	public TaskResponse deleteTask(@PathVariable Long taskId) {
		return this.taskService.deleteTask(taskId);
	}

	//	 MARK TASK AS COMPLETED
	@PutMapping("/{taskId}")
	public TaskResponse markTaskAsCompleted(@PathVariable Long taskId) {
		return this.taskService.markTaskAsCompleted(taskId);

	}
	//	 MARK TASK AS COMPLETED
	@PutMapping("undone/{taskId}")
	public TaskResponse markTaskAsUndone(@PathVariable Long taskId) {
		return this.taskService.markTaskAsUndone(taskId);

	}

	@GetMapping("/sort")
	public List<TaskMaster> sortByTaskTitle(@RequestParam String direction) {
		return this.taskService.sortByTaskTitle(direction);
	}

}
