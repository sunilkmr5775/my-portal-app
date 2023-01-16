package com.sunil.myportal.repository;

import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.model.DatabaseMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DatabaseRepository extends JpaRepository<DatabaseMaster, Long> {


}
