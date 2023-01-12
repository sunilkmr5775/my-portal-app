package com.sunil.myportal.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sunil.myportal.constant.ConstantURL;
import com.sunil.myportal.dto.FileResponse;
import com.sunil.myportal.dto.ImageRequest;
import com.sunil.myportal.dto.ImageResponse;
import com.sunil.myportal.dto.ProfilePicRequest;
import com.sunil.myportal.exception.BadParameterException;
import com.sunil.myportal.model.JobMaster;
import com.sunil.myportal.repository.ProfilePicRepository;
import com.sunil.myportal.service.ProfilePicService;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ConstantURL.Profile_Pic_Controller)
@CrossOrigin("*")
public class ProfilePicController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfilePicController.class);

	@Autowired
	private ProfilePicService profilePicService;
	

	@Autowired
	ProfilePicRepository profilePicRepository;

//	@ApiOperation(value = "Upload Profile Pic", notes = "")
	@PostMapping(ConstantURL.Upload_Profile_Picture)
	public FileResponse UploadPorfilePicture(
			@RequestParam(value = "username", required=true) String username,
			@RequestParam(value = "jobName",  required=true) String jobName,
			@RequestParam(value = "file" ,    required=true) MultipartFile file
	)
			throws FileUploadException, UnsupportedOperationException, URISyntaxException, IOException {

		ProfilePicRequest fileRequest = new ProfilePicRequest();
		fileRequest.setFile(file);
		fileRequest.setJobName(jobName);
		fileRequest.setUsername(username);
		
		return profilePicService.uploadProfilePicture(fileRequest);

	}



//	@ApiOperation(value = "Get Porfile Pic", notes = "")
	@PostMapping(ConstantURL.Fetch_Pic)
	public ImageResponse getImage(@RequestBody ImageRequest ImageRequest) throws IOException, BadParameterException {

		return profilePicService.getImage(ImageRequest);
	}
	
	
//	@ApiOperation(value = "Delete Porfile Pic", notes = "")
	@PostMapping(ConstantURL.Delete_Profile_Pic)
	public FileResponse deleteProfilePic(	
//			@RequestParam(value = "username", required=true) String username,
//			@RequestParam(value = "jobName",  required=true) String jobName
			@RequestBody ProfilePicRequest fileRequest
			) throws IOException, BadParameterException {

//		ProfilePicRequest fileRequest = new ProfilePicRequest();
//		fileRequest.setJobName(jobName);
//		fileRequest.setUsername(username);
		
		return profilePicService.deleteProfilelPic(fileRequest);
	}
	
	
//	@ApiOperation(value = "Get all jobs", notes = "")
	@GetMapping("/getAllJobs")
	public List<JobMaster> getAllJobs()
			throws FileUploadException, UnsupportedOperationException, URISyntaxException, IOException {
		LOGGER.trace("for tracing purpose");
		LOGGER.debug("for debugging purpose");
		LOGGER.info("for informational purpose");
		LOGGER.warn("for warning purpose");
		LOGGER.error("for logging errors");
		return profilePicService.getAllJobs();

	}

}
