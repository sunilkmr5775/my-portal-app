package com.sunil.myportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunil.myportal.model.FileUploadDetails;

@Repository
public interface FileUploadDetailsRepository extends JpaRepository<FileUploadDetails, Long> {



}
