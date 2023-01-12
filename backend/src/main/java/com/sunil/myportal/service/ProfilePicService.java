package com.sunil.myportal.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;

import com.sunil.myportal.dto.FileResponse;
import com.sunil.myportal.dto.ImageRequest;
import com.sunil.myportal.dto.ImageResponse;
import com.sunil.myportal.dto.ProfilePicRequest;
import com.sunil.myportal.exception.BadParameterException;
import com.sunil.myportal.model.JobMaster;

@Service
@Transactional
public interface ProfilePicService {

	List<JobMaster> getAllJobs();

	FileResponse uploadProfilePicture(ProfilePicRequest profilePicRequest) throws URISyntaxException, UnsupportedOperationException, IOException, IllegalStateException, MultipartException, FileUploadException;

	ImageResponse getImage(ImageRequest imageRequest) throws BadParameterException;

	FileResponse deleteProfilelPic(ProfilePicRequest profilePicRequest);

}
