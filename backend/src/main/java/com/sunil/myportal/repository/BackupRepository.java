package com.sunil.myportal.repository;

import com.sunil.myportal.model.Backup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupRepository extends JpaRepository<Backup, Long> {
    // Custom queries or operations can be added here if needed.
}
