package com.sunil.myportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunil.myportal.model.FileLog;

@Repository
public interface FileLogRepository extends JpaRepository<FileLog, Long> {



}
