package com.sunil.myportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunil.myportal.model.ProfilePic;

public interface ProfilePicRepository extends JpaRepository<ProfilePic, Long> {

	ProfilePic findAllByUsername(String username);

	Optional<ProfilePic> findByFileName(String fileName);



}
