package com.sunil.myportal.repository;

import com.sunil.myportal.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    State findByName(String cityName);
}
