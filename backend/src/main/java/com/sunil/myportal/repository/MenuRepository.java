package com.sunil.myportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunil.myportal.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
