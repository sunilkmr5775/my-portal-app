package com.sunil.myportal.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import com.sunil.myportal.constant.ConstantURL;
import com.sunil.myportal.dto.FileRequest;
import com.sunil.myportal.dto.FileResponse;
import com.sunil.myportal.exception.FileUploadException;
import com.sunil.myportal.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin("*")
@RestController
@Component
@RequestMapping(ConstantURL.File_Controller)
public class FileController {

	@Autowired
	FileService fileService;

//	@ApiOperation(value = "Upload File", notes = "")
	@PostMapping(ConstantURL.File_Upload_URL)
	public FileResponse UploadPorfilePicture(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "jobName", required = true) String jobName,
			@RequestParam(value = "file", required = true) MultipartFile file
	)
			throws FileUploadException, UnsupportedOperationException, URISyntaxException, IOException {

		FileRequest fileRequest = new FileRequest();
		fileRequest.setFile(file);
		fileRequest.setJobName(jobName);
		fileRequest.setUserName(username);

		return fileService.uploadFile(fileRequest);

	}

//	@ApiOperation(value = "File Log", notes = "")
//	@PostMapping(ConstantURL.File_Upload_URL)
//	public FileResponse UploadPorfilePicture(@RequestParam(value = "file") MultipartFile file,
//			@RequestParam("jobName") String jobName)
//			throws FileUploadException, UnsupportedOperationException, URISyntaxException, IOException {
//
//		FileRequest fileRequest = new FileRequest();
//		fileRequest.setFile(file);
//		fileRequest.setJobName(jobName);
//
//		return fileService.uploadFile(fileRequest);
//
//	}
}
