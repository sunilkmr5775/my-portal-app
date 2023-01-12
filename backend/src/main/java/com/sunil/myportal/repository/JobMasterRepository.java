package com.sunil.myportal.repository;

import com.sunil.myportal.model.JobMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface JobMasterRepository extends JpaRepository<JobMaster, Long>{

	JobMaster findJobMasterDetailsByJobNameAndStatus(String jobName, String statusActive);

    @Query("SELECT c FROM JobMaster c WHERE (:status is null or c.status = :status) " +
            "and (:jobName is null or c.jobName = :jobName)")
    List<JobMaster> findJobMasterDetailsByStatusAndJobName(@Param("status") String status, @Param("jobName") String jobName);

    JobMaster findJobMasterDetailsByJobName(String jobName);
}
