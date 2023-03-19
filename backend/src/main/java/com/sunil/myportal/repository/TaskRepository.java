package com.sunil.myportal.repository;

import com.sunil.myportal.model.TaskMaster;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<TaskMaster, Long> {

//	@Query(value = "SELECT * FROM BANK_MASTER ORDER BY BANK_NAME", nativeQuery = true)
	public Set<TaskMaster> findAllByOrderByPlannedStartDateAsc();
	
	public Set<TaskMaster> findAllByOrderByTitleDesc();

	@Query("SELECT c FROM TaskMaster c WHERE 1=1 and ( c.title like %:title%) " +
			"and (:taskStatus is null or c.taskStatus = :taskStatus) " +
			"and (c.createdDate >= :taskStartDate and c.createdDate < :taskEndDate )" +
			"order by createdDate DESC")
	List<TaskMaster> findTaskMasterDetailsByTitleAndTaskStatusAndByCreatedDate(
			@Param("title") String title, @Param("taskStatus")String taskStatus,
			@Param("taskStartDate") LocalDate taskStartDate,
			@Param("taskEndDate") LocalDate taskEndDate
	);


	@Query("SELECT c FROM TaskMaster c WHERE 1=1 and " +
			"c.isDeleted = :isDeleted and " +
			" (c.createdDate >= :taskStartDate and c.createdDate < :taskEndDate )" +
			"order by createdDate DESC")
	List<TaskMaster> findAllByIsDeletedAndByCreatedDate(boolean isDeleted,
														@Param("taskStartDate") LocalDate taskStartDate,
														@Param("taskEndDate") LocalDate taskEndDate);

	List<TaskMaster> findAllByTaskStatusAndIsDeleted(String statusPending, boolean isDeleted);
}
