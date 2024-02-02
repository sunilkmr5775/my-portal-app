package com.sunil.myportal.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.sunil.myportal.dto.FileRequest;
import com.sunil.myportal.dto.FileResponse;
import com.sunil.myportal.exception.FileUploadException;

@Service
public interface FileService {

	public FileResponse uploadFile(FileRequest fileRequest) throws FileUploadException, IOException ;

    void store(FileRequest fileRequest) throws IOException;

}
