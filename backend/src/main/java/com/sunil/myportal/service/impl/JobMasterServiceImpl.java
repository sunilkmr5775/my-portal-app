package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.JobRequest;
import com.sunil.myportal.dto.JobResponse;
import com.sunil.myportal.model.JobMaster;
import com.sunil.myportal.repository.JobMasterRepository;
import com.sunil.myportal.service.JobMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobMasterServiceImpl implements JobMasterService {

	@Autowired
	private JobMasterRepository jobMasterRepository;

	@Override
	public JobResponse addNewJob(JobRequest jobRequest) {
		JobResponse jobResponse = new JobResponse();
		JobMaster job = new JobMaster();
		Long jobId = 0L;
		try {

			job.setJobName(jobRequest.getJobName());
			job.setFileType(jobRequest.getFileType());
			job.setFileInPath(jobRequest.getFileInPath());
			job.setFileOutPath(jobRequest.getFileOutPath());
//			job.setEmiAmount(loanRequest.getEmiAmount() != null ? loanRequest.getEmiAmount() : new BigDecimal("0.0"));
			job.setStatus(StatusConstant.STATUS_ACTIVE);
			job.setCreatedBy("sunilkmr5775");
			job.setCreatedDate(LocalDateTime.now());
			job.setModifiedBy(null);
			job.setModifiedDate(null);
			jobId = jobMasterRepository.save(job).getId();
			try {
				if (jobId > 0) {
					jobResponse.setJobName(jobRequest.getJobName());
					jobResponse.setStatus(StatusConstant.STATUS_SUCCESS);
					jobResponse.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_EC);
					jobResponse.setErrorDescription(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);

					return jobResponse;
				}
			} catch (Exception e) {
				jobResponse.setStatus(StatusConstant.STATUS_FAILURE);
				jobResponse.setJobName(jobRequest.getJobName());
				jobResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
				jobResponse.setErrorDescription(e.getMessage());
				return jobResponse;
			}
		} catch (Exception ex) {
			jobResponse.setStatus(StatusConstant.STATUS_FAILURE);
			jobResponse.setJobName(jobRequest.getJobName());
			jobResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
			jobResponse.setErrorDescription(ex.getMessage());
			return jobResponse;
		}
		return jobResponse;
	}

	@Override
	public List<JobMaster> getAllJobs() {
//		return new ArrayList<>(this.bankMasterRepository.findAllByOrderByBankNameAsc());
		return new ArrayList<>(jobMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "jobName")));
	}

	@Override
	public void deleteJob(Long jobId) {
		JobMaster jobMaster = new JobMaster();
		jobMaster.setId(jobId);
		this.jobMasterRepository.delete(jobMaster);

	}

	@Override
	public JobMaster getJobById(Long jobId) {
		JobMaster job = this.jobMasterRepository.findById(jobId).get();
		return job;
	}

	@Override
	public JobMaster getJobByName(String jobName) {
		JobMaster job = this.jobMasterRepository.findJobMasterDetailsByJobName(jobName);
		return job;
	}

	@Override
	public List<JobMaster> sortByJobName(String sortDir) {
		String sortBy = "jobName";
//	     String sortDir = "DESC";

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		List<JobMaster> job = this.jobMasterRepository.findAll(sort);
		return job;
	}

	@Override
	public List<JobMaster> findByJobNameOrStatus(String jobStatus, String jobName) {
		List<JobMaster> jobList = this.jobMasterRepository.findJobMasterDetailsByStatusAndJobName(jobStatus,jobName);
		return jobList;
	}

}
